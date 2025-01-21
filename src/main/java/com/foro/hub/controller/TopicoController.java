package com.foro.hub.controller;

import com.foro.hub.exception.TopicoNotFoundException;
import com.foro.hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Endpoint para eliminar un tópico
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content: Indica que la eliminación fue exitosa
    public void eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe en la base de datos
        if (topicoRepository.existsById(id)) {
            // Eliminar el tópico
            topicoRepository.deleteById(id);
        } else {
            // Lanzar una excepción si el tópico no existe
            throw new TopicoNotFoundException(id);
        }
    }
}