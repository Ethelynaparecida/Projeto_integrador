package br.com.doafood.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.doafood.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);

	public List<Usuario> findByEmailContainingIgnoreCase(String email);

	public List<Usuario> findByCidadeContainingIgnoreCase(String cidade);

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> findByUsuario(String usuario);

	public Optional<Usuario> findById(Usuario idUsuario);

	public Optional<Usuario> findByCnpj(String usuarioCnpj);
	

}
