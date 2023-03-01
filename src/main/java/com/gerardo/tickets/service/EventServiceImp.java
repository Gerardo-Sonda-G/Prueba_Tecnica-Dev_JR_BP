package com.gerardo.tickets.service;

import com.gerardo.tickets.exceptions.BadRequest;
import com.gerardo.tickets.exceptions.NotFound;
import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    @Override
    public Event getById(long id) {
        //get the event, if exists return the event in other case return error
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return event.get();
        } else {
            throw new NotFound("No se encuentra el evento con el id proporcionado");
        }
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event create(Event event) {
        //get dates in milliseconds(long)
        long startMillis = Long.parseLong(event.getStart_date());
        long endMillis = Long.parseLong(event.getEnd_date());
        long nowMillis = System.currentTimeMillis();
        //check date consistency
        if (startMillis < nowMillis) {
            throw new NotFound("La fecha de inicio no puede ser menor a la fecha actual");
        }
        if (endMillis < startMillis) {
            throw new NotFound("La fecha de fin no puede ser menor o igual a la fecha de inicio");
        }
        //check if number_tickets is in the range from 1 to 300
        if (event.getTickets_number() < 1 || event.getTickets_number() > 300) {
            throw new NotFound("El numero de tickets deben estar en el rango de 1 a 300");
        }
        //convert milliseconds to a timestamp
        Timestamp startTimestamp = new Timestamp(startMillis);
        Timestamp endTimestamp = new Timestamp(endMillis);
        //update dates in event object
        event.setStart_date(startTimestamp.toString());
        event.setEnd_date(endTimestamp.toString());
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        //get the event if exist update in other case return error
        Optional<Event> optionalEvent = eventRepository.findById(event.getId());
        if (optionalEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }
        //get dates in milliseconds(long)
        long startMillis = Long.parseLong(event.getStart_date());
        long endMillis = Long.parseLong(event.getEnd_date());
        long nowMillis = System.currentTimeMillis();
        //check date consistency and tickets consistency
        if (startMillis < nowMillis) {
            throw new NotFound("La fecha de inicio no puede ser menor a la fecha actual");
        }
        if (endMillis < startMillis) {
            throw new NotFound("La fecha de fin no puede ser menor o igual a la fecha de inicio");
        }
        if (event.getTickets_number() < 1 || event.getTickets_number() > 300) {
            throw new NotFound("El numero de tickets deben estar en el rango de 1 a 300");
        }
        Event updateEvent = optionalEvent.get();
        if (event.getTickets_number() < updateEvent.getSold_tickets()) {
            throw new BadRequest("La cantidad de tickets no pueden ser inferiores a los vendidos");
        }
        //convert milliseconds to a timestamp
        Timestamp startTimestamp = new Timestamp(startMillis);
        Timestamp endTimestamp = new Timestamp(endMillis);
        //update dates in event object
        event.setStart_date(startTimestamp.toString());
        event.setEnd_date(endTimestamp.toString());
        return eventRepository.save(event);
    }

    @Override
    public Event remove(long id) {
        //get the event, if exist delete in other case return the error
        Optional<Event> removedEvent = eventRepository.findById(id);
        if (removedEvent.isEmpty()) {
            throw new NotFound("No existe el evento indicado");
        }
        Event event = removedEvent.get();
        LocalDateTime localDateTime = LocalDateTime.parse(event.getEnd_date(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long endMillis = localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();
        //in this section verify data consistency and sold tickets
        if (event.getSold_tickets() == 0) {
            eventRepository.deleteById(id);
        } else if (endMillis > System.currentTimeMillis()) {
            throw new BadRequest("No se puede borrar el evento si la fecha de fin no es mayor a la fecha actual");
        } else {
            eventRepository.deleteById(id);
        }
        return removedEvent.orElse(null);
    }
}
