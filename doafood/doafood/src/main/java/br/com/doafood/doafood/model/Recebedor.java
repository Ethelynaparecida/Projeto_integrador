package br.com.doafood.doafood.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_recebedor")
public class Recebedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Campo descrição não pode ser Nulo")
	@Size(min = 6, max = 50)
	private String nome;
	@NotNull
	@Size(min = 10, max = 50)
	private String email;
	@NotNull
	@Size(min = 8, max = 15, message = "A senha deve ter entre 8 à 15 caracteres")
	private String senha;
	@NotNull
	@Size(min = 6, max = 100)
	private String cidade;
	@NotNull
	@Size(min = 6, max = 100)
	private String bairro;
	@NotNull
	private Integer telefone;
	
	@ManyToMany
	private Set<Comunidade> comunidade = new HashSet<>();
	
	
}
