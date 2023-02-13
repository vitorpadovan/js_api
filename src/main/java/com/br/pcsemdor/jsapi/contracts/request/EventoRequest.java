package com.br.pcsemdor.jsapi.contracts.request;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class EventoRequest {

	private String nomeEvento;

	private Date dataEvento;

	private Date dataPublicacao;

	private int idMissao;

	private boolean saveTheDate;

	private MultipartFile file;
}
