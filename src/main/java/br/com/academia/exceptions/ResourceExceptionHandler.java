package br.com.academia.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), "Objeto não encontrado",  exception.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request)
	{
		ValidationError error = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), System.currentTimeMillis(), "Erro de validação", exception.getMessage(), request.getRequestURI());
		
		for(FieldError err: exception.getBindingResult().getFieldErrors())
		{
			error.addError(err.getField(), err.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), System.currentTimeMillis(), "Acesso negado", exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), "Erro de arquivo", exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException exception, HttpServletRequest request)
	{
		HttpStatus code = HttpStatus.valueOf(exception.getErrorCode());
		
		StandardError error = new StandardError(code.value(), System.currentTimeMillis(), "Erro Amazon Service", exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(code).body(error);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), "Erro Amazon Client", exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception exception, HttpServletRequest request)
	{
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), "Erro Amazon S3", exception.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
