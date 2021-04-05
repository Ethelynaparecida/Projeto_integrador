package br.com.doafood.doafood.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_doador")
public class Doador {

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
	private Integer cnpj;

	@OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("doador")
	private List<Comunidade> comunidade;

	public List<Comunidade> getComunidade() {
		return comunidade;
	}

	public void setComunidade(List<Comunidade> comunidade) {
		this.comunidade = comunidade;
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

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}

}
