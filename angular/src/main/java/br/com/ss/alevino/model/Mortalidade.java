package br.com.ss.alevino.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "pisc_mortalidade")
public class Mortalidade extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 4312444146472691158L;

	@Id
	@GeneratedValue(generator = "SEQ_PIS_MORTALIDADE", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PIS_MORTALIDADE", name = "SEQ_PIS_MORTALIDADE")
	@Column(name = "ID_PIS_MORTALIDADE")
	private Long id;

	@Column(nullable = false)
	private Date data;

	@Column(nullable = false)
	private int quantidade;

	@ManyToOne
	@JoinColumn(name = "ID_CICLO_TANQUE")
	private CicloTanque periodoTanque;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public CicloTanque getPeriodoTanque() {
		return periodoTanque;
	}

	public void setPeriodoTanque(CicloTanque periodoTanque) {
		this.periodoTanque = periodoTanque;
	}
}