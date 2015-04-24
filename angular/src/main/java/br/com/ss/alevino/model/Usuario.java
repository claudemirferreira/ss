package br.com.ss.alevino.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author altitdb
 */
@Entity
@Table(name = "SAA_USUARIO")
public class Usuario extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = -7789936704890560797L;

	@Id
	@GeneratedValue(generator = "SEQ_SAA_USUARIO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_SAA_USUARIO", name = "SEQ_SAA_USUARIO")
	@Column(name = "ID_SAA_USUARIO")
	private Long id;

	@Column(nullable = false, length = 30)
	@NotNull
	private String nome;

	@Column(unique = true, length = 30)
	@NotNull
	private String login;

	@Column(length = 64)
	private String senha;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}