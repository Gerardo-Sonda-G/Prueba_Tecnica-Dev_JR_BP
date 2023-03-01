package com.gerardo.tickets.models;

import jakarta.persistence.*;
import lombok.Data;

//Definicion del modelo de tickets
//Antacion data para generar los setter y getters mediante lombok
//Anotacion table para indicar el nombre de la tabla a la cual pertenece el modelo
@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    //Deffinir para cada elemento su columna indicandola con la antacion @Column
    //Indicar el id de la tabla con la anotacion @Id
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
