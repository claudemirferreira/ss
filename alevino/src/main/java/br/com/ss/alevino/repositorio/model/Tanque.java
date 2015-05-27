package br.com.ss.alevino.repositorio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "pisc_tanque")
@XmlRootElement
public class Tanque extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7583941438325843350L;

	@Id
	@GeneratedValue(generator = "SEQ_PIS_TANQUE", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PIS_TANQUE", name = "SEQ_PIS_TANQUE")
	@Column(name = "ID_PIS_TANQUE")
	private Long id;

	@Column(length = 20, unique = true, nullable = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}