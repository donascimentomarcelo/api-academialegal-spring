package br.com.academia.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request)
	{
		ValidationError error = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError err: exception.getBindingResult().getFieldErrors())
		{
			error.addError(err.getField(), err.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
}
