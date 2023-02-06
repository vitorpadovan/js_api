package com.br.pcsemdor.jsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_nome_perfil", columnNames = "nomePerfil"))
public class PerfilUsuario implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "perfil_seq")
	@SequenceGenerator(name = "perfil_seq", sequenceName = "perfil_seq", allocationSize = 1)
	private int idPerfil;

	private String nomePerfil;


	@Override
	public String getAuthority() {
		return this.nomePerfil;
	}
}
