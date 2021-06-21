package vitorlucas.desafiozup.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;

import vitorlucas.desafiozup.entities.Endereco;
import vitorlucas.desafiozup.entities.Usuario;

public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	
	@CPF(message = "CPF invalido")
	private String cpf;
	
	@Email(message = "Email invalido")
	private String email;
	
	@Past(message = "A data deve ser  do passado")
	private Instant dataNascimento;
	
	private List<EnderecoDTO> enderecos = new ArrayList<>();

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, String nome, String cpf, String email, Instant dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		cpf = entity.getCpf();
		email = entity.getEmail();
		dataNascimento = entity.getDataNascimento();
	}
	
	public UsuarioDTO(Usuario entity, List<Endereco> enderecos) {
		this(entity);
		enderecos.forEach(x -> this.enderecos.add(new EnderecoDTO(x)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}
	
}
