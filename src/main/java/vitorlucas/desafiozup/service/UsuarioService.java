package vitorlucas.desafiozup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vitorlucas.desafiozup.dto.UsuarioDTO;
import vitorlucas.desafiozup.entities.Usuario;
import vitorlucas.desafiozup.repository.UsuarioRepository;
import vitorlucas.desafiozup.service.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	

	@Transactional(readOnly = true)
	public UsuarioDTO findByCpf(String cpf) {
		cpf = formataCpf(cpf);		
		Usuario usuario = repository.findByCpf(cpf);
		if (usuario == null) {
			throw new ResourceNotFoundException("cpf não encontrado");
		}
		return new UsuarioDTO(usuario, usuario.getEnderecos());
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setCpf(dto.getCpf());
		usuario.setEmail(dto.getEmail());
		usuario.setDataNascimento(dto.getDataNascimento());
		usuario = repository.save(usuario);
		return new UsuarioDTO(usuario);
	}


	private String formataCpf(String cpf) {
		cpf = cpf.replaceAll("\\.", "");
		cpf = cpf.replaceAll("-", "");
		return cpf;
	}
}
