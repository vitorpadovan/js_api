package com.br.pcsemdor.jsapi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.br.pcsemdor.jsapi.model.enums.TipoEvento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evento {

	@Id
	@GeneratedValue(generator = "evento_seq")
	@SequenceGenerator(name = "evento_seq", sequenceName = "evento_seq", allocationSize = 1)
	private int idEvento;

	private String nomeEvento;

	private Date dataEvetno;

	private Date dataPublicacao;

	private boolean saveTheDate = false;

	@Lob
	@Column(name = "imagedata", length = 1000)
	private byte[] imageData;

	@ManyToOne
	@JoinColumn(name = "idMissao", foreignKey = @ForeignKey(name = "fk_Evento_idMissao"))
	private Missao missao;

	private TipoEvento tipoEvento;
}
