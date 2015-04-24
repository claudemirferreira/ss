package br.com.ss.alevino.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "pisc_ciclo")
public class Ciclo extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 3253009373044273132L;

	@Id
	@GeneratedValue(generator = "SEQ_PISC_CICLO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PISC_CICLO", name = "SEQ_PISC_CICLO")
	@Column(name = "ID_PISC_CICLO")
	private Long id;

	@Column(nullable = false, length = 6)
	private String periodo;

	@Column(nullable = false)
	private Date dataInicio;

	@Column(nullable = false)
	private Date dataFim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}