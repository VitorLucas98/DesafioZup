package vitorlucas.desafiozup.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vitorlucas.desafiozup.dto.UsuarioDTO;
import vitorlucas.desafiozup.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		Page<UsuarioDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<UsuarioDTO> findByCpf(@PathVariable String cpf){
		UsuarioDTO dto = service.findByCpf(cpf);
		return ResponseEntity.ok(dto);
	} 
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand(dto.getCpf()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{cpf}")
	public ResponseEntity<UsuarioDTO> update(@RequestBody @Valid UsuarioDTO dto, @PathVariable String cpf){
		dto = service.update(dto, cpf);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<Void> delete(@PathVariable String cpf){
		service.delete(cpf);
		return ResponseEntity.noContent().build();
	}
	
}
