package br.com.doafood.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.doafood.model.Comunidade;

@Repository
public interface ComunidadeRepository extends JpaRepository<Comunidade, Long> {
	public Optional<Comunidade> findByNomeContainingIgnoreCase(String Nome);

	public List<Comunidade> findByDescricaoContainingIgnoreCase(String descricao);

	public Comunidade findByNome(String nome);

	public Optional<Comunidade> findById(Long id);
}
