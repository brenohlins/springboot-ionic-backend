package com.brenolins.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brenolins.cursomc.service.validations.ClienteInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ClienteInsert
public class ClienteNewDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Obrigatório preencher")
	@Length(min = 4, max = 80, message = "Minimo 4 caracteres, máximo 80")
	private String nome;

	@NotEmpty(message = "Obrigatorio preencher")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Obrigatorio preencher")
	private String cpfOuCnpj;
	
	private Integer tipo;

	@NotEmpty(message = "Obrigatorio preencher")
	private String logradouro;
	
	@NotEmpty(message = "Obrigatorio preencher")
	private String numero;

	private String complemento;
	
	private String bairro;

	@NotEmpty(message = "Obrigatorio preencher")
	private String cep;

	@NotEmpty(message = "Obrigatorio preencher")
	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;

}
