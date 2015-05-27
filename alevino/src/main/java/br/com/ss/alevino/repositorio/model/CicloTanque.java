package br.com.ss.alevino.repositorio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private int quanidadePeixe;

	// @EmbeddedId
	// private CicloTanquePk cicloTanquePk = new CicloTanquePk();

	@Transient
	private Tanque tanque;

	@Transient
	private Ciclo ciclo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuanidadePeixe() {
		return quanidadePeixe;
	}

	public void setQuanidadePeixe(int quanidadePeixe) {
		this.quanidadePeixe = quanidadePeixe;
	}

	/*
	 * public CicloTanquePk getCicloTanquePk() { return cicloTanquePk; }
	 * 
	 * public void setCicloTanquePk(CicloTanquePk cicloTanquePk) {
	 * this.cicloTanquePk = cicloTanquePk; }
	 */

	public Tanque getTanque() {
		return tanque;
	}

	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

}