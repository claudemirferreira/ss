package br.com.ss.piscicultura.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pisc_racao")
public class Racao extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 8990225168526557596L;

	@Id
	@GeneratedValue(generator = "SEQ_PISC_RACAO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PISC_RACAO", name = "SEQ_PISC_RACAO")
	@Column(name = "ID_PIS_RACAO")
	private Long id;

	@Column(nullable = false)
	private float preco;

	@Column(unique = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}