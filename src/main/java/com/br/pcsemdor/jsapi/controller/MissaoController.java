package com.br.pcsemdor.jsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pcsemdor.jsapi.business.MissaoBusiness;
import com.br.pcsemdor.jsapi.model.Missao;

@RestController
@RequestMapping("/api/missao")
public class MissaoController {

	private MissaoBusiness business;


	public MissaoController(
			MissaoBusiness business) {
		super();
		this.business = business;
	}

	@GetMapping
	public ResponseEntity<List<Missao>> getMissoes() {
		return ResponseEntity.ok(business.getMissoes());
	}
}
