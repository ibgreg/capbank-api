package com.ibgregorio.capbank.dto;

import java.io.Serializable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.ibgregorio.capbank.domain.Transacao;

public class TransacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Campo obrigatório")
	@Digits(integer = 6, fraction = 0, message = "A conta deve ter no máximo 6 dígitos")
	private Integer conta;
	
	private Integer contaOrigem;
	
	@NotNull(message = "Campo obrigatório")
	private Double valor;

	private Integer operacao;
	
	public TransacaoDTO() {
	}
	
	public TransacaoDTO(Transacao transacao) {
		super();
		conta = transacao.getConta().getNumConta();
		valor = transacao.getValor();
		operacao = transacao.getOperacao().getCodigo();
	}

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}
	
	public Integer getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Integer contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getOperacao() {
		return operacao;
	}

	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}
	
}
