package com.foro.hub.repository;

import com.foro.hub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // JpaRepository ya proporciona el método deleteById y existsById
}