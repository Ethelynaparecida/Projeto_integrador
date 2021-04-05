package br.com.doafood.doafood.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doafood.doafood.model.Comunidade;

@Repository
public interface ComunidadeRepository extends JpaRepository<Comunidade, Long>{
	public List<Comunidade>findAllByNomeContainingIgnoreCase (String Nome);
	public List<Comunidade>findByDescricaoContainingIgnoreCase(String descricao);
}

