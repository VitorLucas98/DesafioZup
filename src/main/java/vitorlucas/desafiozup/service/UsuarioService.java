package vitorlucas.desafiozup.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vitorlucas.desafiozup.dto.UsuarioDTO;
import vitorlucas.desafiozup.entities.Usuario;
import vitorlucas.desafiozup.repository.UsuarioRepository;
import vitorlucas.desafiozup.service.exceptions.DatabaseException;
import vitorlucas.desafiozup.service.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional
	public Page<UsuarioDTO> findAllPaged(Pageable pageable){
		Page<Usuario> list = repository.findAll(pageable);
		return list.map(x -> new UsuarioDTO(x));
	}

	@Transactional(readOnly = true)
	public UsuarioDTO findByCpf(String cpf) {
		var usuario = findCpf(cpf);
		return new UsuarioDTO(usuario, usuario.getEnderecos());
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		copyDtoToEntity(usuario, dto);
		usuario.setCpf(dto.getCpf());
		usuario.setEmail(dto.getEmail());
		usuario = repository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO update(UsuarioDTO dto, String cpf) {
		var usuario = findCpf(cpf);
		copyDtoToEntity(usuario, dto);
		usuario = repository.save(usuario);
		return new UsuarioDTO(usuario);
	}

	private String formataCpf(String cpf) {
		cpf = cpf.replaceAll("\\.", "");
		cpf = cpf.replaceAll("-", "");
		return cpf;
	}
	
	public void delete(String cpf) {
		try {			
			repository.delete(findCpf(cpf));
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Usuario não encontrado");
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação na integridade dos dados");
		}
	}
	
	private void copyDtoToEntity(Usuario usuario, UsuarioDTO dto) {
		usuario.setNome(dto.getNome());
		usuario.setDataNascimento(dto.getDataNascimento());
		
	}
	
	private Usuario findCpf(String cpf) {
		Usuario usuario = repository.findByCpf(formataCpf(cpf));
		if (usuario == null) {
			throw new ResourceNotFoundException("cpf não encontrado");
		}
		return usuario;
	}
}
