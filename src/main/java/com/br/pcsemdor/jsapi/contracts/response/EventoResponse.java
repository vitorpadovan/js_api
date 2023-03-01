package com.br.pcsemdor.jsapi.contracts.response;

import java.sql.Date;

import com.br.pcsemdor.jsapi.model.Missao;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EventoResponse {

	private int idEvento;

	private String nomeEvento;

	private Date dataEvento;

	private Date dataPublicacao;

	private Missao missao;

	private boolean saveTheDate;
}
