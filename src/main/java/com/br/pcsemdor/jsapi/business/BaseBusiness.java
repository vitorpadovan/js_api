package com.br.pcsemdor.jsapi.business;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseBusiness {

	protected Object getUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			var usuarioLogado = auth.getPrincipal();
			if (usuarioLogado == "anonymousUser")
				throw new RuntimeException("Não logado");
			return usuarioLogado;
		} catch (Exception ex) {
			throw new RuntimeException("Impossível de verificar o usuário logado, erro: " + ex.getMessage());
		}
	}
}
