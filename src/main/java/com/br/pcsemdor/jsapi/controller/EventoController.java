package com.br.pcsemdor.jsapi.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.pcsemdor.jsapi.business.EventoBusiness;
import com.br.pcsemdor.jsapi.contracts.request.EventoRequest;
import com.br.pcsemdor.jsapi.model.Evento;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

	private EventoBusiness _business;


	public EventoController(
			EventoBusiness evento) {
		super();
		this._business = evento;
	}

	@GetMapping("/imagem")
	public ResponseEntity<?> imagem() {
		try {
			byte[] image = _business.getImage(4);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
		} catch (NotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping
	public ResponseEntity<Evento> salvarEvento(EventoRequest file) {
		var response = _business.salvarEvento(file);
		return ResponseEntity.ok(response);
	}
}
