package com.ibgregorio.capbank.domain.enums;

public enum Operacao {
	
	SAQUE(1, "Saque"),
	DEPOSITO(2, "Depósito");

	private int codigo;
	
	private String descricao;

	private Operacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Operacao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Operacao x : Operacao.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
}
