package com.gerardo.tickets.graphql.mutation;

import com.gerardo.tickets.models.Ticket;
import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.service.EventService;
import com.gerardo.tickets.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class TicketMutation {
    private final TicketService ticketService;
    private final EventService eventService;
    @MutationMapping
    public Ticket createTicket(@Argument("input") Ticket ticket){
        return ticketService.create(ticket);
    }

    @MutationMapping
    public Ticket changeTicket(@Argument("id") long id, @Argument("email") String email){
        return ticketService.changeTicket(id,email);
    }

    @SchemaMapping
    public Event event(Ticket ticket) {
        return eventService.getById(ticket.getEvent_id());
    }
}
