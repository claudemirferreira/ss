package br.com.ss.alevino.repositorio.model;

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
@Table(name = "pisc_lancamento_racao")
public class LancamentoRacao extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 7611825065286242048L;

	@Id
	@GeneratedValue(generator = "SEQ_PIS_LANCAMENTO_RACAO", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "SEQ_PIS_LANCAMENTO_RACAO", name = "SEQ_PIS_LANCAMENTO_RACAO")
	@Column(name = "ID_PIS_LANCAMENTO_RACAO")
	private Long id;

	@Column(nullable = false)
	private int quantidade;

	@Column(nullable = false)
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
