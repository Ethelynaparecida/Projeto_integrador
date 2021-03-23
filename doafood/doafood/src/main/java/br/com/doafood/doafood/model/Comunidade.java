package br.com.doafood.doafood.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JsonIgnoreProperties("comunidade")
	private Doador doador;

	

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
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

}
