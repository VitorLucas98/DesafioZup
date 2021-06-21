package vitorlucas.desafiozup.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError() {
	}
	
	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public void adicionaErro(String fieldName, String message) {
		erros.add(new FieldMessage(fieldName, message));
	}

	public List<FieldMessage> getErros() {
		return erros;
	}
}
