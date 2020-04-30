package com.Divsoft.Service.validators;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.Divsoft.Entidade.Funcionario;
import com.Divsoft.Repository.FuncionarioRepository;


public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, Funcionario> {

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public boolean isValid(Funcionario value, ConstraintValidatorContext context) {
		if(request == null) {
			return true;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long id = null;
		if(map.get("id") != null) {
			id = Long.parseLong(map.get("id"));
		}
		
		Funcionario aux = repository.findByEmail(value.getemail());
		if(aux != null) {
			if(id == null || !id.equals(aux.getId())) {				
				context.disableDefaultConstraintViolation();
				context
				.buildConstraintViolationWithTemplate("Já existe um usuário com o e-mail informado")
				.addPropertyNode("email")
				.addConstraintViolation();
				return false;
			}
		}
		return true;
		
	}
	

}
