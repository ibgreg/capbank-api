package com.ibgregorio.capbank.domain.enums;

public enum TipoTransacao {
	
	CREDITO("C", "Crédito"),
	DEBITO("D", "Débito");

	private String codigo;
	
	private String descricao;

	private TipoTransacao(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}


	public String getDescricao() {
		return descricao;
	}
	
	public static TipoTransacao toEnum(String cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoTransacao x : TipoTransacao.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido: " + cod);
	}
	
}
