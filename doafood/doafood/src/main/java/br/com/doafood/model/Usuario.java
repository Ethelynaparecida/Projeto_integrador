package br.com.doafood.model;

import java.util.HashSet;
import java.util.Set;

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
	private String telefone;
	
	private String tipo;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "inscritos",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "comunidade_id"))
	@JsonIgnoreProperties({"meusInscritos", "usuarioCriador"})
	private Set<Comunidade> minhasInscricoes = new HashSet<>();

	@OneToMany(mappedBy = "usuarioCriador", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"usuarioCriador","meusInscritos"})
	private Set<Comunidade> minhasComunidades = new HashSet<>();

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

	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Set<Comunidade> getMinhasInscricoes() {
		return minhasInscricoes;
	}

	public void setMinhasInscricoes(Set<Comunidade> minhasInscricoes) {
		this.minhasInscricoes = minhasInscricoes;
	}

	public Set<Comunidade> getMinhasComunidades() {
		return minhasComunidades;
	}

	public void setMinhasComunidades(Set<Comunidade> minhasComunidades) {
		this.minhasComunidades = minhasComunidades;
	}

	
}
