package br.com.doafood.doafood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doafood.doafood.model.Doador;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
	public List<Doador> findAllByNomeContainingIgnoreCase(String nome);

	public Optional<Doador> findByNome(String nome);

	public List<Doador> findByBairroContainingIgnoreCase(String bairro);

	public Doador findByEmailContainingIgnoreCase(String email);

	public Optional<Doador> findByEmail(String email);

	public Optional<Doador> findByUsuario(String usuario);

}
