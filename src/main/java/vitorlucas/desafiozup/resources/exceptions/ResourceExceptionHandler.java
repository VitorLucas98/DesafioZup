package vitorlucas.desafiozup.resources.exceptions;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import vitorlucas.desafiozup.service.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError erro = new StandardError(LocalDate.now(), status.value(),
				"Recurso não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}
	
	/*@ExceptionHandler(PersistenceException .class)
	public ResponseEntity<StandardError> constraintViolation(PersistenceException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError erro = new StandardError(LocalDate.now(), status.value(),
				"Registro duplicado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	
	}
	*/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError erro = new ValidationError(LocalDate.now(), status.value(),
				"Erro de validação", e.getMessage(), request.getRequestURI());
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			erro.adicionaErro(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(erro);
	}

}
