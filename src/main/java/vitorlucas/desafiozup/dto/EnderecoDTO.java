package vitorlucas.desafiozup.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import vitorlucas.desafiozup.entities.Endereco;


public class EnderecoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String logradouro;
	
	@Positive(message = "Numero deve ser positivo")
	private Integer numero;
	private String complemento;
	
	@NotBlank(message = "Campo obrigatório")
	private String bairro;
	
	@NotBlank(message = "Campo obrigatório")
	private String localidade;
	
	@NotBlank(message = "Campo obrigatório")
	private String uf;
	
	@NotBlank(message = "Campo obrigatório")
	private String cep;
	
	private Long idUsuario;
	
	public EnderecoDTO() {
	}

	public EnderecoDTO(Long id, String logradouro, Integer numero, String complemento, String bairro, String localidade,
			String uf, String cep, Long idUsuario) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.cep = cep;
		this.idUsuario = idUsuario;
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
		idUsuario = entity.getUsuario().getId();
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

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
