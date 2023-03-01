package com.br.pcsemdor.jsapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pcsemdor.jsapi.model.Missao;

public interface MissaoRepo extends JpaRepository<Missao, Integer> {
}
