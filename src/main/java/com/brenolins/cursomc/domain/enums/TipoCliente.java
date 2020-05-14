package com.brenolins.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
		
	}
	public static TipoCliente toEnum (Integer cod) {
		if (cod == null) {
			return null;
			
		}
		for (TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
				
			}
		throw new IllegalArgumentException("Id inválido: "+cod);
		}
		return null;
		
		
	}
}
