package com.foro.hub.dto;

import javax.validation.constraints.NotBlank;

public class TopicoDTO {

    @NotBlank(message = "El título es obligatorio.")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio.")
    private String mensaje;

    private Long autorId; // Esto se utilizará para asociar el autor al tópico

    private Long cursoId; // Esto se utilizará para asociar el curso al tópico

    // Getters and Setters
}