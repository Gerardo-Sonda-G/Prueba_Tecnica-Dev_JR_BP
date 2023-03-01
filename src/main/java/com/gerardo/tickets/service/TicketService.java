package com.gerardo.tickets.service;

import com.gerardo.tickets.models.Ticket;

public interface TicketService {
    /**
     * Before save the tickets check:
     * if the event exists in other case return error
     * @param ticket data with the Tickets model structure without id.
     * @return ticket data if is created correctly.
     */
    Ticket create(Ticket ticket);
    /**
     * Before change the ticket check:
     * if the ticket exist,
     * if the ticket has been changed before,
     * in other case return a respective error
     * @param id identifier of tickets.
     * @param email buyer email
     * @return ticket data if is created correctly.
     */
    Ticket changeTicket(long id, String email);
}
