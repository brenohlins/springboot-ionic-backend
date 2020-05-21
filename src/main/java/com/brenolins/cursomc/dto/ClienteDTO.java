
package com.brenolins.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.brenolins.cursomc.domain.Cliente;
import com.brenolins.cursomc.service.validations.ClienteUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ClienteUpdate

public class ClienteDTO implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Obrigatório preencher")
	@Length(min=4, max =80,message="Minimo 4 caracteres, máximo 80")
	private String nome;
	
	@NotEmpty(message="Obrigatorio preencher")
	@Email(message="Email inválido")
	private String email;
	
	
	public ClienteDTO(Cliente obj){
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}
	
	
}
