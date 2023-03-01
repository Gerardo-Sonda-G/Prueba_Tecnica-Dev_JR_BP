package com.gerardo.tickets.repository;

import com.gerardo.tickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Count the number of sold tickets of indicate event
     * @param event_id identifier of event.
     * @return sold tickets of event .
     */
    @Transactional
    @Query(value = "SELECT count(id) FROM tickets where event_id = ?1 ", nativeQuery = true)
    int getTicketsSold(long event_id);

    /**
     * Count the number of changed tickets of indicate event
     * @param event_id identifier of event.
     * @return changed tickets of event .
     */
    @Transactional
    @Query(value = "SELECT count(id) FROM tickets where event_id = ?1 and changed = true", nativeQuery = true)
    int getTicketsChanged(long event_id);

    /**
     * Update the ticket status to changed
     * @param id identifier of ticket.
     * @param email bayer email
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE tickets SET changed = true where id = ?1 and email = ?2", nativeQuery = true)
    void changeTicket(long id, String email);
}
