
package br.com.doafood.service;

import java.nio.charset.Charset;
import java.util.Optional;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.doafood.model.Comunidade;
import br.com.doafood.model.Usuario;
import br.com.doafood.model.UsuarioLogin;
import br.com.doafood.repository.ComunidadeRepository;
import br.com.doafood.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repositoryUsuario;

	private @Autowired ComunidadeRepository repositoryComunidade;

	public Optional<Usuario> cadastrarUsuario(Usuario novoUsuario) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findByEmail(novoUsuario.getEmail());
		if (usuarioExistente.isPresent()) {
			return Optional.empty();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(novoUsuario.getSenha());
		novoUsuario.setSenha(senhaCriptografada);
		return Optional.ofNullable(repositoryUsuario.save(novoUsuario));
	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioPresente = repositoryUsuario.findByEmail(usuarioLogin.get().getEmail());

		if (usuarioPresente.isPresent()) {
			if (encoder.matches(usuarioLogin.get().getSenha(), usuarioPresente.get().getSenha())) {
				String auth = usuarioLogin.get().getEmail() + ":" + usuarioLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioLogin.get().setToken(authHeader);
				usuarioLogin.get().setEmail(usuarioPresente.get().getEmail());
				usuarioLogin.get().setSenha(usuarioPresente.get().getSenha());

				return usuarioLogin;
			}
		}
		return null;
	}

	/*
	 * public Comunidade inscreverComunidade(Comunidade novaInscricao, Usuario
	 * usuario) {
	 * 
	 * Comunidade comunidadeExistente =
	 * repositoryComunidade.findByNome(novaInscricao.getNome());
	 * 
	 * Usuario recebedorExistente = repositoryUsuario.findAllById(usuario.getId()));
	 * //findById(usuario.getId());
	 * 
	 * if (!recebedorExistente.equals(null) .isEmpty()) {
	 * comunidadeExistente.setUsuarioInscrito(repositoryUsuario.findById(usuario.
	 * getId())/*setUsuarioInscrito());
	 * 
	 * return repositoryComunidade.save(comunidadeExistente); } return null; }
	 */

	public Optional<Usuario> inscreverComunidade(Long idComunidade, Long idUsuario) {

		Optional<Comunidade> comunidade = repositoryComunidade.findById(idComunidade);

		Optional<Usuario> recebedor = repositoryUsuario.findById(idComunidade);

		if (comunidade.isPresent() && recebedor.isPresent()) {

			recebedor.get().getMinhascomunidades().add(comunidade.get());

			return Optional.ofNullable(repositoryUsuario.save(recebedor.get()));

		} else {
			return Optional.empty();
		}

	}

	public Optional<Usuario> visualizarPerfil(String usuario) {

		Optional<Usuario> usuarioExistente = repositoryUsuario.findByEmail(usuario);
		if (usuarioExistente.isPresent()) {
			return usuarioExistente;
		} else {
			return Optional.empty();
		}

	}

	// Apagarcomunidade

	public Optional<Usuario> deletarComunidade(Long idComunidade, String UsuarioCnpj) {
		Optional<Usuario> doadorExistente = repositoryUsuario.findByCnpj(UsuarioCnpj);
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);

		if (doadorExistente.isPresent() && comunidadeExistente.isPresent()) {
			comunidadeExistente.get().setUsuarioCriador(null);
			repositoryComunidade.save(comunidadeExistente.get());
			repositoryComunidade.deleteById(comunidadeExistente.get().getId());
			return repositoryUsuario.findById(doadorExistente.get().getId());
		}
		return null;
	}

	// doador Criar Comunidade

	public Comunidade cadastrarComunidade(Comunidade novaComunidade, Long UsuarioId) {
		Comunidade comunidadeExistente = repositoryComunidade.save(novaComunidade);

		Optional<Usuario> doadorExistente = repositoryUsuario.findById(UsuarioId);
		if (doadorExistente.isPresent()) {
			comunidadeExistente.setUsuarioCriador(doadorExistente.get());
			return repositoryComunidade.save(comunidadeExistente);
		}
		return null;
	}

	// Apagarcomunidade

	/*
	 * public Optional<Usuario> deletarComunidade(Long idComunidade, String
	 * UsuarioCnpj) { Optional<Usuario> doadorExistente =
	 * repositoryUsuario.findByCnpj(UsuarioCnpj); Optional<Comunidade>
	 * comunidadeExistente = repositoryComunidade.findById(idComunidade);
	 * 
	 * if(doadorExistente.isPresent()&&comunidadeExistente.isPresent()) {
	 * comunidadeExistente.get().setUsuarioCriador(null);
	 * repositoryComunidade.save(comunidadeExistente.get());
	 * repositoryComunidade.deleteById(comunidadeExistente.get().getId()); return
	 * repositoryUsuario.findById(doadorExistente.get().getId()); } return null; }
	 * 
	 * //doador Criar Comunidade
	 * 
	 * public Comunidade cadastrarComunidade (Comunidade novaComunidade, String
	 * UsuarioCnpj) { Comunidade comunidadeExistente =
	 * repositoryComunidade.save(novaComunidade);
	 * 
	 * Optional<Usuario> doadorExistente =
	 * repositoryUsuario.findByCnpj(UsuarioCnpj); if(doadorExistente.isPresent()) {
	 * comunidadeExistente.setUsuarioCriador(doadorExistente.get()); return
	 * repositoryComunidade.save(comunidadeExistente); } return null; }
	 */
}
