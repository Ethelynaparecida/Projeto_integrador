package br.com.doafood.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Campo descrição não pode ser Nulo")
	@Size(min = 2)
	private String nome;
	@NotNull
	@Size(min = 2)
	private String email;
	@NotNull
	@Size(min = 8, message = "A senha deve ter entre 8 à 15 caracteres")
	private String senha;
	@NotNull
	@Size(min = 6)
	private String cidade;
	@NotNull
	@Size(min = 6)
	private String bairro;
	@NotNull
	private Integer telefone;
	@NotNull
	private String cnpj;
	@NotNull
	private String cpf;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
	  name = "inscrito", 
	  joinColumns = @JoinColumn(name = "comunidade_id"), 
	  inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	@JsonIgnoreProperties("usuario")
	private List<Comunidade> comunidade = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Comunidade comunidadeCriada;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
