package com.gerardo.tickets.service;

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
    @Override
    public Ticket changeTicket(long event_id, String email) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(event_id);
        return optionalTicket.orElse(null);
    }

    @Override
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
