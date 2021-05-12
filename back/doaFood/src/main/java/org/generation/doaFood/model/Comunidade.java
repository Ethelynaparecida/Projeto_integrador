package org.generation.doaFood.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_comunidade")
public class Comunidade {

	

		@Id	
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		
		@NotNull
		private String nome;
		@NotNull
		private String cidade;
		@NotNull
		private String sobre;
		
		
		
		@OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL)
		@JsonIgnoreProperties("comunidade")
		private List<Postagem> postagem;



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getNome() {
			return nome;
		}



		public void setNome(String nome) {
			this.nome = nome;
		}



		public String getCidade() {
			return cidade;
		}



		public void setCidade(String cidade) {
			this.cidade = cidade;
		}



		public String getSobre() {
			return sobre;
		}



		public void setSobre(String sobre) {
			this.sobre = sobre;
		}



		public List<Postagem> getPostagem() {
			return postagem;
		}



		public void setPostagem(List<Postagem> postagem) {
			this.postagem = postagem;
		}

		
		
}

