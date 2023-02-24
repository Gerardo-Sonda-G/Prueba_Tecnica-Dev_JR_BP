package com.gerardo.tickets.service;

import com.gerardo.tickets.exceptions.NotFound;
import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Event getById(long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event remove(long id) {
        Optional<Event> removedEvent = eventRepository.findById(id);
        if (removedEvent.isPresent()) {
            eventRepository.deleteById(id);
        }
        return removedEvent.orElse(null);
    }
}