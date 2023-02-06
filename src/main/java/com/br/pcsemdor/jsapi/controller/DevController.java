package com.br.pcsemdor.jsapi.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pcsemdor.jsapi.contracts.request.LoginRequest;
import com.br.pcsemdor.jsapi.model.Usuario;
import com.br.pcsemdor.jsapi.repo.UsuarioRepo;

@RestController
@Profile("dev")
@RequestMapping("/dev")
public class DevController {

	private int dias = 2;

	private UsuarioRepo repoUsuario;


	public DevController(
			UsuarioRepo repoUsuario) {
		super();
		this.repoUsuario = repoUsuario;
	}

	@PostMapping
	private Usuario gerarSenha(@RequestBody LoginRequest login) {
		long intervalo = dias * 24 * 60 * 60;
		BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
		Usuario u = new Usuario();
		u.setLogin(login.getLogin());
		u.setSenha(bCrypt.encode(login.getSenha()));
		// u.setDataValidade(new Date(System.currentTimeMillis()));
		System.out.println(intervalo + " - " + System.currentTimeMillis());
		repoUsuario.save(u);
		login.setSenha(u.getSenha());
		return u;
	}

	@GetMapping
	public String teste() {
		return "vitor";
	}
}
