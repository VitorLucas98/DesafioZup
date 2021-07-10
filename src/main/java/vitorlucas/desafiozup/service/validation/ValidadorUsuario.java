package vitorlucas.desafiozup.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import vitorlucas.desafiozup.dto.UsuarioDTO;
import vitorlucas.desafiozup.entities.Usuario;
import vitorlucas.desafiozup.repository.UsuarioRepository;
import vitorlucas.desafiozup.resources.exceptions.FieldMessage;

public class ValidadorUsuario implements ConstraintValidator<UsuarioValido, UsuarioDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UsuarioValido ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> lista = new ArrayList<>();
		
		Usuario usu1 = repository.findByEmail(dto.getEmail());
		if (usu1 != null) {
			lista.add(new FieldMessage("email", "Email já cadastrado"));
		}
		
		Usuario usu2 = repository.findByCpf(dto.getCpf());
		
		if (usu2 != null) {
			lista.add(new FieldMessage("cpf", "cpf já cadastrado"));			
		}
		
		for (FieldMessage e : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lista.isEmpty();
	}
}
