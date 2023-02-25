package com.gerardo.tickets.exceptions;

public class BadRequest extends RuntimeException {
    //Error de informacion erronea en la peticion
    public BadRequest(String message) {
        super(message);
    }
}
