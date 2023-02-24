package com.gerardo.tickets.service;

import com.gerardo.tickets.models.Event;

import java.util.List;

public interface EventService {
    Event getById(long id);

    List<Event> getAll();

    Event create(Event event);

    Event update(Event event);

    Event remove(long id);
}
