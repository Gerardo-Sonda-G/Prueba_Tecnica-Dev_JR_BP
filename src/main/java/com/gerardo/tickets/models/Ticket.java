package com.gerardo.tickets.models;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="ticket_date")
    private String ticket_date;
    @Column(name="email")
    private String email;
    @Column(name="event_id")
    private long event_id;
    @Column(name="changed")
    private boolean changed;

}
