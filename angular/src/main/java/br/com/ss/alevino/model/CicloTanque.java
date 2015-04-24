package br.com.ss.alevino.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pisc_ciclo_tanque")
public class CicloTanque extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7705285151866432624L;

	@Id
	@GeneratedValue(generator = "SEQ_PIS_CICLO_TANQUE", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PIS_CICLO_TANQUE", name = "SEQ_PIS_CICLO_TANQUE")
	@Column(name = "ID_PIS_CICLO_TANQUE")
	private Long id;

	@Column(nullable = false)
	private int quantidadePeixe;

	@ManyToOne
	@JoinColumn(name = "ID_PISC_CICLO", nullable = false)
	private Ciclo ciclo;

	@ManyToOne
	@JoinColumn(name = "ID_PISC_TANQUE", nullable = false)
	private Tanque tanque;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

	public int getQuantidadePeixe() {
		return quantidadePeixe;
	}

	public void setQuantidadePeixe(int quantidadePeixe) {
		this.quantidadePeixe = quantidadePeixe;
	}

}