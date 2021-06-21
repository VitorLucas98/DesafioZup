package vitorlucas.desafiozup.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import vitorlucas.desafiozup.dto.UsuarioDTO;
import vitorlucas.desafiozup.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<UsuarioDTO> findByCpf(@PathVariable String cpf){
		UsuarioDTO dto = service.findByCpf(cpf);
		return ResponseEntity.ok(dto);
	} 
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody @Valid UsuarioDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getCpf()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
