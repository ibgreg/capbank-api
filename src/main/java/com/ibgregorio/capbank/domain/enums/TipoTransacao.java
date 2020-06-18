package com.ibgregorio.capbank.domain.enums;

public enum TipoTransacao {
	
	CREDITO(1, "Crédito"),
	DEBITO(2, "Débito");

	private int codigo;
	
	private String descricao;

	private TipoTransacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoTransacao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoTransacao x : TipoTransacao.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
