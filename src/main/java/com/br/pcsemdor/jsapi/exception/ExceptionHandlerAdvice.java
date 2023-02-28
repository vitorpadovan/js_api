package com.br.pcsemdor.jsapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> teste(RuntimeException ex) {
		// TODO fazer de uma maneira mais amigável tratando usando um DTO
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		String retorno = "";
		// TODO fazer de uma maneira mais amigável tratando usando um DTO
		for (var t : ex.getAllErrors()) {
			retorno = retorno.concat(t.getDefaultMessage() + " ");
		}
		return ResponseEntity.badRequest().body(retorno);
	}
}
