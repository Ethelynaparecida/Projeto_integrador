package br.com.doafood.doafood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.model.Recebedor;

@Repository
public interface RecebedorRepository extends JpaRepository<Recebedor, Long>{

	public List<Recebedor> findAllByNomeContainingIgnoreCase (String nome);

	public List<Recebedor> findByEmailContainingIgnoreCase(String email);

	public List<Recebedor> findByCidadeContainingIgnoreCase(String cidade);


	public Optional<Recebedor> findByEmail(String email);

	public Optional<Recebedor> findByRecebedor(String recebedor);
	
	public Optional<Recebedor> findById(Long id);

}
