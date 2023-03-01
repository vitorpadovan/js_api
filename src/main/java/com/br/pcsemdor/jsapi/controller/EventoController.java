package com.br.pcsemdor.jsapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.pcsemdor.jsapi.business.EventoBusiness;
import com.br.pcsemdor.jsapi.contracts.request.EventoRequest;
import com.br.pcsemdor.jsapi.contracts.response.EventoResponse;
import com.br.pcsemdor.jsapi.model.Evento;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/evento")
@Log4j2
public class EventoController {

	private EventoBusiness _business;


	public EventoController(
			EventoBusiness evento) {
		super();
		this._business = evento;
	}

	@PostMapping
	public ResponseEntity<Evento> salvarEvento(@RequestPart(name = "file", required = false) MultipartFile file,
			@Valid EventoRequest request) {
		log.info(request.toString());
		var response = _business.salvarEvento(request, file);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<EventoResponse>> getEventos() {
		List<EventoResponse> r = null;
		r = this._business.getEventos().stream().map(x -> {
			log.info(x.getMissao());
			// TODO adicionar https://mapstruct.org/
			var response = new EventoResponse();
			response.setIdEvento(x.getIdEvento());
			response.setDataEvento(x.getDataEvetno());
			response.setDataPublicacao(x.getDataPublicacao());
			response.setNomeEvento(x.getNomeEvento());
			response.setSaveTheDate(x.isSaveTheDate());
			response.setMissao(x.getMissao());
			return response;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(r);
	}

	@GetMapping("/imagem/{id}")
	public ResponseEntity<Object> getImagem(@PathVariable int id) {
		return ResponseEntity.ok(this._business.teste2(id));
	}
}
