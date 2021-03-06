package br.com.doafood.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_comunidade")
public class Comunidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 6, max = 50)
	private String nome;

	@NotNull
	@Size(min = 10, max = 250)
	private String sobre;

	@NotNull
	@Size(min = 6, max = 100)
	private String bairro;

	@OneToMany(mappedBy = "publiComunidade", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"publiComunidade","meusInscritos","minhasComunidades"})
	private Set<Publicacao> publicacao = new HashSet<>();

	@ManyToMany(mappedBy = "minhasInscricoes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"publiComunidade", "minhasInscricoes"})
	private Set<Usuario> meusInscritos = new HashSet<>();

	// Mudei para Manytoone e coloquei um nome para a coluna
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "criador")
	@JsonIgnoreProperties({"minhasInscricoes","minhasComunidades"})
	private Usuario usuarioCriador;

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


	public String getSobre() {
		return sobre;
	}

	public void setSobre(String sobre) {
		this.sobre = sobre;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	

	public Set<Publicacao> getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Set<Publicacao> publicacao) {
		this.publicacao = publicacao;
	}

	public Set<Usuario> getMeusInscritos() {
		return meusInscritos;
	}

	public void setMeusInscritos(Set<Usuario> meusInscritos) {
		this.meusInscritos = meusInscritos;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
}
