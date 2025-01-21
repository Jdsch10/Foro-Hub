package com.foro.hub.exception;

public class TopicoNotFoundException extends RuntimeException {
    public TopicoNotFoundException(Long id) {
        super("Tópico no encontrado con ID: " + id);
    }
}