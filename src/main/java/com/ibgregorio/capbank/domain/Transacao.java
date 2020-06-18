package com.ibgregorio.capbank.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ibgregorio.capbank.domain.enums.TipoTransacao;

@Entity
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	private Double valor;
	
	private Integer tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dataTransacao;
	
	public Transacao() {
	}

	public Transacao(Integer id, Conta conta, Double valor, TipoTransacao tipo, LocalDateTime dataTransacao) {
		super();
		this.id = id;
		this.conta = conta;
		this.valor = valor;
		this.tipo = (tipo == null) ? null : tipo.getCodigo();
		this.dataTransacao = dataTransacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoTransacao getTipo() {
		return TipoTransacao.toEnum(tipo);
	}

	public void setTipo(TipoTransacao tipo) {
		this.tipo = tipo.getCodigo();
	}

	public LocalDateTime getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(LocalDateTime dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}