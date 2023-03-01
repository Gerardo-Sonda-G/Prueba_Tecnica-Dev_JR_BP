package com.gerardo.tickets.service;

import com.gerardo.tickets.exceptions.BadRequest;
import com.gerardo.tickets.exceptions.NotFound;
import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.models.Ticket;
import com.gerardo.tickets.repository.EventRepository;
import com.gerardo.tickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImp implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Override
    public Ticket changeTicket(long id, String email) {
        //check if exist the tickets in other case return error
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isEmpty()) {
            throw new NotFound("No existe el ticket a indicado");
        }
        Ticket ticket = optionalTicket.get();
        //check if the ticket has been changed before if is true return error in other case update the ticket
        if(ticket.isChanged()){
            throw new BadRequest("El ticket ya ha sido cambiado anteriormente");
        }
        Optional<Event> optionalEvent = eventRepository.findById(ticket.getEvent_id());
        if (optionalEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }
        Event event = optionalEvent.get();

        LocalDateTime localDateTimeEnd = LocalDateTime.parse(event.getEnd_date(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long endMillis = localDateTimeEnd
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
        LocalDateTime localDateTimeStart = LocalDateTime.parse(event.getStart_date(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long startMillis = localDateTimeStart
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();

        if (endMillis < System.currentTimeMillis() || startMillis > System.currentTimeMillis()){
            throw new BadRequest("El boleto solo se puede cambiar en el periodo de inicio y fin del evento");
        }

        ticketRepository.changeTicket(id, email);
        event.setChanged_tickets(ticketRepository.getTicketsChanged(ticket.getEvent_id()));
        eventRepository.save(event);
        return optionalTicket.orElse(null);
    }

    @Override
    public Ticket create(Ticket ticket) {
        //check if exist the event in other case return error
        Optional<Event> optionalEvent = eventRepository.findById(ticket.getEvent_id());
        if (optionalEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }
        long dateMillis = Long.parseLong(ticket.getTicket_date());
        Timestamp startTimestamp = new Timestamp(dateMillis);

        ticket.setTicket_date(startTimestamp.toString());
        Ticket newTicket = ticketRepository.save(ticket);
        Event event = optionalEvent.get();
        event.setSold_tickets(ticketRepository.getTicketsSold(event.getId()));
        eventRepository.save(event);
        return newTicket;
    }
}
