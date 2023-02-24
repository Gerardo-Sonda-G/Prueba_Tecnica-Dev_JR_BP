package com.gerardo.tickets.graphql.query;

import com.gerardo.tickets.models.Event;
import com.gerardo.tickets.service.EventService;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
//se usa el AllArgsConstructor para inicializar el eventService automaticamente
public class EventQuery {
    private final EventService eventService;
    // declaracion de la query para obtener todos los eventos
    @QueryMapping
    public List<Event> getEvents(){
        return this.eventService.getAll();
    }
    // declaracion de la query para obtener un evento por su id
    @QueryMapping
    public Event  getEvent(@Argument("id") long id){
        return  this.eventService.getById(id);
    }
}
