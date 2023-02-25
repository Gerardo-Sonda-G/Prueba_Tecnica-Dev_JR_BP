package com.gerardo.tickets.service;

import com.gerardo.tickets.exceptions.BadRequest;
import com.gerardo.tickets.exceptions.NotFound;
import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.models.Ticket;
import com.gerardo.tickets.repository.EventRepository;
import com.gerardo.tickets.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImp implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    @Override
    public Ticket changeTicket(long event_id, String email) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(event_id);
        if (optionalTicket.isEmpty()) {
            throw new NotFound("No existe el ticket a indicado");
        }
        Ticket ticket = optionalTicket.get();
        if(ticket.isChanged()){
            throw new BadRequest("El ticket ya ha sido cambiado anteriormente");
        }
        Optional<Event> optionalEvent = eventRepository.findById(ticket.getEvent_id());
        if (optionalEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }

        return optionalTicket.orElse(null);
    }

    @Override
    public Ticket create(Ticket ticket) {
        Optional<Event> optionalEvent = eventRepository.findById(ticket.getEvent_id());
        if (optionalEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }
        return ticketRepository.save(ticket);
    }
}
