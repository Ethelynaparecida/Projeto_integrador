package org.generation.doaFood.repository;

import java.util.List;

import org.generation.doaFood.model.Comunidade;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ComunidadeRepository extends JpaRepository<Comunidade, Long> {
	public List<Comunidade> findAllByNomeContainingIgnoreCase( String nome);
}