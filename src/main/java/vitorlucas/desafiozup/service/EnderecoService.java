package vitorlucas.desafiozup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vitorlucas.desafiozup.dto.EnderecoDTO;
import vitorlucas.desafiozup.entities.Endereco;
import vitorlucas.desafiozup.entities.Usuario;
import vitorlucas.desafiozup.feignclients.ViaCepFeignClient;
import vitorlucas.desafiozup.repository.EnderecoRepository;
import vitorlucas.desafiozup.repository.UsuarioRepository;
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
	public EnderecoDTO findById(Long id) {
		Optional <Endereco> end = repository.findById(id);
		Endereco endereco = end.orElseThrow(() -> new ResourceNotFoundException("Endereco not found"));
		return new EnderecoDTO(endereco);
	}
	
	@Transactional
	public EnderecoDTO insert(EnderecoDTO dto) {
		Endereco entity = viaCepFeignClient.findyEnderecoByCep(dto.getCep());
		entity.setNumero(dto.getNumero());
		entity.setComplemento(dto.getComplemento());

		Usuario usuario = usuarioRepository.getOne(dto.getIdUsuario());
		entity.setUsuario(usuario);

		entity = repository.save(entity);
		return new EnderecoDTO(entity);
	}
}
