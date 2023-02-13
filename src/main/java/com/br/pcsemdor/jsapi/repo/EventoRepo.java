package com.br.pcsemdor.jsapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pcsemdor.jsapi.model.Evento;

public interface EventoRepo extends JpaRepository<Evento, Integer> {
}
