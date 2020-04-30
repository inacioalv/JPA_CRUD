package com.Divsoft.Resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.Divsoft.Entidade.StandarError;
import com.Divsoft.Entidade.ValidationError;
import com.Divsoft.Exception.DataIntegrityException;
import com.Divsoft.Exception.objectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(objectNotFoundException.class)
	public ResponseEntity<StandarError>objectNotFound(objectNotFoundException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(),e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
		
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandarError>DataIntegrityException(DataIntegrityException e, HttpServletRequest request){
		StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> ValidException(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Erro de validação");
		for(FieldError o : e.getBindingResult().getFieldErrors()) {
			err.addError(o.getField(), o.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
