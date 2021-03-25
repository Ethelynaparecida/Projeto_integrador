package br.com.doafood.doafood.repository;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doafood.doafood.model.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long>{

	public List<Publicacao> findAllByCategoriaContainingIgnoreCase (String categoria);
}
