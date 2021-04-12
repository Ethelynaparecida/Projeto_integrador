package br.com.doafood.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@Size(min = 50, max = 250)
	private String descricao;

	@NotNull
	@Size(min = 6, max = 100)
	private String bairro;

	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("publicacao")
	private List<Publicacao> publiComunidade = new ArrayList<>();

	/*
	 * @ManyToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, fetch =
	 * FetchType.EAGER)
	 * 
	 * @JsonIgnoreProperties("comunidade") private Usuario usuarioInscrito;
	 */

	@ManyToMany(mappedBy = "minhascomunidades", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("minhascomunidades")
	private List<Usuario> inscritoPor = new ArrayList<>();

	// Mudei para Manytoone e coloquei um nome para a coluna
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "criador")
	@JsonIgnoreProperties("comunidadeCriada")
	private Usuario usuarioCriador;

	/*
	 * public Usuario getUsuarioInscrito() { return usuarioInscrito; }
	 * 
	 * public void setUsuarioInscrito(Usuario usuarioInscrito) {
	 * this.usuarioInscrito = usuarioInscrito; }
	 */

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<Publicacao> getPubliComunidade() {
		return publiComunidade;
	}

	public void setPubliComunidade(List<Publicacao> publiComunidade) {
		this.publiComunidade = publiComunidade;
	}

	public List<Usuario> getInscritoPor() {
		return inscritoPor;
	}

	public void setInscritoPor(List<Usuario> inscritoPor) {
		this.inscritoPor = inscritoPor;
	}

	public void setInscritoPor(Long id2) {
		// TODO Auto-generated method stub
		
	}

	

}
