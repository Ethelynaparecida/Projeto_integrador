package br.com.doafood.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_publicacao")
public class Publicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// quantidade de alimentos para doacao
	@NotNull
	private Integer quantidade;

	// tipo de alimentos para doacao
	@NotNull
	private String categoria;

	// recebedors cadastrados para doação
	@ElementCollection
	private List<Usuario> inscricao = new ArrayList<Usuario>();
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	private String descricao;

	@ManyToOne
	@JsonIgnoreProperties({"publicacao","meusInscritos","minhasComunidades"})
	private Comunidade publiComunidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	

	public List<Usuario> getInscricao() {
		return inscricao;
	}

	public void setInscricao(List<Usuario> inscricao) {
		this.inscricao = inscricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Comunidade getPubliComunidade() {
		return publiComunidade;
	}

	public void setPubliComunidade(Comunidade publiComunidade) {
		this.publiComunidade = publiComunidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
		

}
