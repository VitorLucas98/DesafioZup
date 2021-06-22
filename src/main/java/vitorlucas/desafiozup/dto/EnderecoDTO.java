package vitorlucas.desafiozup.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import vitorlucas.desafiozup.entities.Endereco;


public class EnderecoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Length(min = 5, max = 60, message = "Quantidade de caracteres invalida")
	@NotBlank(message = "Campo obrigatório")
	private String logradouro;
	
	@Positive(message = "Numero deve ser positivo")
	private Integer numero;
	
	@Length(min = 2, max = 60, message = "Quantidade de caracteres invalida")
	private String complemento;
	
	@Length(min = 2, max = 60, message = "Quantidade de caracteres invalida")
	@NotBlank(message = "Campo obrigatório")
	private String bairro;
	
	@Length(min = 2, max = 50, message = "Quantidade de caracteres invalida")
	@NotBlank(message = "Campo obrigatório")
	private String localidade;
	
	@Length(min = 2, max = 2, message = "Quantidade de caracteres invalida")
	@NotBlank(message = "Campo obrigatório")
	private String uf;
	
	@Length(min = 8 , max = 8, message = "Quantidade de caracteres invalida")
	@NotBlank(message = "Campo obrigatório")
	private String cep;
	
	private String cpfUsuario;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String logradouro, Integer numero, String complemento, String bairro, String localidade,
			String uf, String cep,  String cpfUsuario) {
		this.id = id; 
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.cep = cep;
		this.cpfUsuario = cpfUsuario;
	}
	
	public EnderecoDTO(Endereco entity) {
		id = entity.getId();
		logradouro = entity.getLogradouro();
		numero = entity.getNumero();
		complemento = entity.getComplemento();
		bairro = entity.getBairro();
		localidade = entity.getLocalidade();
		uf = entity.getUf();
		cep = entity.getCep();
		cpfUsuario = entity.getUsuario().getCpf();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
}
