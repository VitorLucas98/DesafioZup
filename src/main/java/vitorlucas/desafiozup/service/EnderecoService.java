package vitorlucas.desafiozup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vitorlucas.desafiozup.dto.EnderecoDTO;
import vitorlucas.desafiozup.dto.EnderecoFeignClient;
import vitorlucas.desafiozup.entities.Endereco;
import vitorlucas.desafiozup.entities.Usuario;
import vitorlucas.desafiozup.feignclients.ViaCepFeignClient;
import vitorlucas.desafiozup.repository.EnderecoRepository;
import vitorlucas.desafiozup.repository.UsuarioRepository;
import vitorlucas.desafiozup.service.exceptions.DatabaseException;
import vitorlucas.desafiozup.service.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ViaCepFeignClient viaCepFeignClient;

	@Transactional(readOnly = true)
	public Page<EnderecoDTO> findAllPaged(Pageable pageable){
		Page<Endereco> list = repository.findAll(pageable);
		return list.map(x -> new EnderecoDTO(x));
	}
	
	@Transactional(readOnly = true)
	public EnderecoDTO findById(Long id) {
		Optional<Endereco> end = repository.findById(id);
		Endereco endereco = end.orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
		return new EnderecoDTO(endereco);
	}
	
	@Transactional(readOnly = true)
	public EnderecoFeignClient findEnderecoByCep(String cep) {
		EnderecoFeignClient end = viaCepFeignClient.findByCep(cep);
		return end;
	}

	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = new Endereco();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	@Transactional
	public EnderecoDTO update(EnderecoDTO dto, Long id) {
		Endereco entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Endereco não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação da integridade");
		}

	}
	
	private void copyDtoToEntity(EnderecoDTO dto, Endereco entity) {
		entity.setLogradouro(dto.getLogradouro());
		entity.setBairro(dto.getBairro());
		entity.setLocalidade(dto.getLocalidade());
		entity.setUf(dto.getUf());
		entity.setCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());

		Usuario usuario = usuarioRepository.findByCpf(dto.getCpfUsuario());
		if (usuario == null) {
			throw new ResourceNotFoundException("usuario não encontrado");
		}
		entity.setUsuario(usuario);
	}
}
