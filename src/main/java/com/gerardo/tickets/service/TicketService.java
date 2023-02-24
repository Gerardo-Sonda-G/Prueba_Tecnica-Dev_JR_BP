package com.gerardo.tickets.service;

import com.gerardo.tickets.models.Ticket;

public interface TicketService {
    Ticket create(Ticket ticket);
    Ticket changeTicket(long event_id, String email);
}
