package com.br.pcsemdor.jsapi.business;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.pcsemdor.jsapi.model.Usuario;
import com.br.pcsemdor.jsapi.repo.UsuarioRepo;

@Component
public class UsuarioBusiness implements UserDetailsService {

	private UsuarioRepo repoUsuario;


	public UsuarioBusiness(
			UsuarioRepo repoUsuario) {
		super();
		this.repoUsuario = repoUsuario;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optUsuariou = repoUsuario.findByLogin(username);
		System.out.println("Pesquisando o login");
		if (!optUsuariou.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		return optUsuariou.get();
	}
}
