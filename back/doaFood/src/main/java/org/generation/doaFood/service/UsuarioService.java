package org.generation.doaFood.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.doaFood.model.Comunidade;
import org.generation.doaFood.model.Postagem;
import org.generation.doaFood.model.Usuario;
import org.generation.doaFood.model.UsuarioLogin;
import org.generation.doaFood.repository.ComunidadeRepository;
import org.generation.doaFood.repository.PostagemRepository;
import org.generation.doaFood.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PostagemRepository Postrepository;

	public Usuario CadastrarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent()) {

			return null;

		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());

		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);

	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repository.findByEmail(user.get().getEmail());
		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getEmail() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(usuario.get().getId());
				user.get().setNome(usuario.get().getNome());
				user.get().setTipo(usuario.get().getTipo());
				user.get().setEmail(usuario.get().getEmail());
				user.get().setSenha(usuario.get().getSenha());
				user.get().setTelefone(usuario.get().getTelefone());
				

				return user;
			}
		}

		return null;
	}
	
	public Optional<Postagem> inscreverComunidade(Long idComunidade, Long idUsuario) {

		Optional <Postagem> postagem = Postrepository.findById(idComunidade);

		Optional<Usuario> usuario = repository.findById(idUsuario);

		if (postagem.isPresent() && usuario.isPresent()) {

			postagem.get().getInscricao().add(usuario.get());

			return Optional.ofNullable(Postrepository.save(postagem.get()));

		} else {
			return Optional.empty();
		}

	}
	
	
	
	
	
	public Optional<Usuario> atualizarUsuario(Usuario usuario){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return Optional.of(repository.save(usuario));
	}

}
