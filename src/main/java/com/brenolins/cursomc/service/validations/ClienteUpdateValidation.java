package com.brenolins.cursomc.service.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.brenolins.cursomc.domain.Cliente;
import com.brenolins.cursomc.dto.ClienteDTO;
import com.brenolins.cursomc.repository.ClienteRepository;
import com.brenolins.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	@Autowired
	
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Integer uriID = Integer.parseInt(map.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();

		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux!=null && !aux.getId().equals(uriID)){
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}