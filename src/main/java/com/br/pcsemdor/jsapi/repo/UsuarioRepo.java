package com.br.pcsemdor.jsapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pcsemdor.jsapi.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByLogin(String usuario);
}
