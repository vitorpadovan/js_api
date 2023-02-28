package com.br.pcsemdor.jsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Missao {

	@Id
	@GeneratedValue(generator = "missao_seq")
	@SequenceGenerator(name = "missao_seq", sequenceName = "missao_seq", allocationSize = 1)
	private int idMissao;

	private String nomeMissao;

	private String cidade;

	private String estado;

	private String endereco;
}
