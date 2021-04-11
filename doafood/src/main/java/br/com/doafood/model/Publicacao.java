package br.com.doafood.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_publicacao")
public class Publicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//quantidade de alimentos para doacao
	@NotNull
	private Integer quantidade;
	
	//tipo de alimentos para doacao
	@NotNull
	private String categoria;
	
	
	//recebedors cadastrados para doação
	 private String inscricao;
	 
	 
	 private String	 descricao;
	 
	 @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
	 @JsonIgnoreProperties("publicacao")
	 private List<Comunidade> comunidade;


	}

