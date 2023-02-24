package com.gerardo.tickets.graphql.mutation;

import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@AllArgsConstructor
public class EventMutation {
    private final EventService eventService;
    @MutationMapping
    public Event createEvent(@Argument("input") Event event){
        return this.eventService.create(event);
    }
    @MutationMapping
    public Event deleteEvent(@Argument("id") long id){
      return this.eventService.remove(id);
    }
    @MutationMapping
    public Event updateEvent(@Argument("input") Event event){
        return this.eventService.update(event);
    }

}
