package com.gerardo.tickets.repository;

import com.gerardo.tickets.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Transactional
    @Query(value = "SELECT count(id) FROM tickets where event_id = ?1 ", nativeQuery = true)
    int getTicketsSold(long event_id);

    @Transactional
    @Query(value = "SELECT count(id) FROM tickets where event_id = ?1 and changed = true", nativeQuery = true)
    int getTicketsChanged(long event_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tickets SET changed = true where event_id = ?1 and email = ?2", nativeQuery = true)
    void changeTicket(long event_id, String email);
}
