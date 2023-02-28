package com.br.pcsemdor.jsapi.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(name = "uk_login", columnNames = "login"))
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "usuario_seq")
	@SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
	private int idUsuario;

	private String login;

	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "JTUsuarioPerfil", joinColumns = @JoinColumn(
					name = "idUsuario", foreignKey = @ForeignKey(name = "fk_JTUsuarioPerfil_idUsuario")
			), inverseJoinColumns = @JoinColumn(
					name = "idPeril", foreignKey = @ForeignKey(name = "fk_JTUsuarioPerfil_idPeril")
			)
	)
	private List<PerfilUsuario> perfis;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
