package vitorlucas.desafiozup.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vitorlucas.desafiozup.dto.EnderecoFeignClient;

@Component
@FeignClient(url ="https://viacep.com.br/ws/", name = "viacep" )
public interface ViaCepFeignClient {
	
	@GetMapping("{cep}/json")
	EnderecoFeignClient findByCep(@PathVariable("cep") String cep);
}
