package com.gerardo.tickets.models;

import jakarta.persistence.*;
import lombok.Data;

//Definicion del modelo de Events
//Antacion data para generar los setter y getters mediante lombok
//Anotacion table para indicar el nombre de la tabla a la cual pertenece el modelo
@Data
@Entity
@Table(name = "events")
public class Event {
    //Deffinir para cada elemento su columna indicandola con la antacion @Column
    //Indicar el id de la tabla con la anotacion @Id
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
    @Column(name="start_date")
    private String start_date;
    @Column(name="end_date")
    private String end_date;
    @Column(name="tickets_number")
    private int tickets_number;
    @Column(name="sold_tickets")
    private int sold_tickets;
    @Column(name="changed_tickets")
    private int changed_tickets;
    @Column(name="address")
    private String address;
}
