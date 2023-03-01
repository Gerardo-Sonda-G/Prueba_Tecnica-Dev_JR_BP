package com.gerardo.tickets.service;

import com.gerardo.tickets.models.Event;

import java.util.List;

//Definicion de la firma los metodos del servicio
public interface EventService {
    /**
     * get one event from database
     * @param id identification of event.
     * @return event data.
     */
    Event getById(long id);
    /**
     * Get all events from database
     * @return a List of Events
     */
    List<Event> getAll();

    /**
     * Before save the event check:
     * date consistency - start date will never be earlier than the current date,
     * end date will never be earlier than the start date
     *  the tickets_number must be in range from 1 to 300
     * @param event data with the Event model structure without id.
     * @return event data if is created correctly.
     */
    Event create(Event event);

    /**
     * Before update the event check:
     * date consistency - start date will never be earlier than the current date,
     * end date will never be earlier than the start date
     * the tickets_number must be in range from 1 to 300
     * tickets number will never be less than sold tickets
     *
     * @param event data with the Event model structure with id.
     * @return event data if is created correctly.
     */
    Event update(Event event);

    /**
     * Before delete the event, check consistency of dates and tickets number with tickets sold
     * only delete if tickets sold is zero or if end_date the date has passed
     *
     * @param id id of event for delete.
     * @return event data if is created correctly.
     */
    Event remove(long id);
}
