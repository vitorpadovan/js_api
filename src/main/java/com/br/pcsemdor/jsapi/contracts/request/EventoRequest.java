package com.br.pcsemdor.jsapi.contracts.request;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EventoRequest {

	@NotBlank(message = "Nome do evento não pode ser vazio")
	@NotNull(message = "Nome do evento não pode ser nulo")
	private String nomeEvento;

	@NotNull(message = "Data do evento é obrigatória")
	private Date dataEvento;

	private Date dataPublicacao;

	@NotNull(message = "Missão é obrigatória")
	private Integer idMissao;

	private boolean saveTheDate;
}
