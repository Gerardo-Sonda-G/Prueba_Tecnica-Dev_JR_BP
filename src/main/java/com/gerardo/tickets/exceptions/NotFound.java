package com.gerardo.tickets.exceptions;
public class NotFound extends RuntimeException {
    //Error de infomracion no encontrada
    public NotFound(String message) {
        super(message);
    }
}
