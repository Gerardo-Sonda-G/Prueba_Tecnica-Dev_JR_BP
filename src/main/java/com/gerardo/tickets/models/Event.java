package com.gerardo.tickets.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="type")
    private String type;
    @Column(name="amount")
    private int amount;
    @Column(name="start_term")
    private String start_term;
    @Column(name="end_term")
    private String end_term;
    @Column(name="event_date")
    private String event_date;
    @Column(name="tickets_number")
    private int tickets_number;
    @Column(name="sold_tickets")
    private int sold_tickets;
    @Column(name="changed_tickets")
    private int changed_tickets;
    @Column(name="address")
    private String address;
}
