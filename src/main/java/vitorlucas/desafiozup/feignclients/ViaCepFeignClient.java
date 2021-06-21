package vitorlucas.desafiozup.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vitorlucas.desafiozup.entities.Endereco;

@Component
@FeignClient(url ="https://viacep.com.br/ws/", name = "viacep" )
public interface ViaCepFeignClient {
	
	@GetMapping("{cep}/json")
	Endereco findyEnderecoByCep(@PathVariable("cep") String cep);
}
