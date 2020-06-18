package com.ibgregorio.capbank.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibgregorio.capbank.domain.enums.Operacao;
import com.ibgregorio.capbank.domain.enums.TipoTransacao;

@Entity
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_conta_origem")
	private Conta contaOrigem;
	
	private Double valor;
	
	private String tipo;
	
	private Integer operacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTransacao;
	
	public Transacao() {
	}

	public Transacao(Integer id, Conta conta, Conta contaOrigem, Double valor, TipoTransacao tipo, Operacao operacao, Date dataTransacao) {
		super();
		this.id = id;
		this.conta = conta;
		this.contaOrigem = contaOrigem;
		this.valor = valor;
		this.tipo = (tipo == null) ? null : tipo.getCodigo();
		this.operacao = (operacao == null) ? null : operacao.getCodigo();
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

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
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
	
	public Operacao getOperacao() {
		return Operacao.toEnum(operacao);
	}

	public void setOperacao(Operacao oper) {
		this.operacao = oper.getCodigo();
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
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