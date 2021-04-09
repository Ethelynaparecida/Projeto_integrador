
package br.com.doafood.doafood.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.model.Recebedor;
import br.com.doafood.doafood.model.UsuarioLogin;
import br.com.doafood.doafood.repository.ComunidadeRepository;

import br.com.doafood.doafood.repository.RecebedorRepository;

@Service
public class RecebedorService {

	private @Autowired RecebedorRepository repositoryRecebedor;

	private @Autowired ComunidadeRepository repositoryComunidade;

	public Optional<Recebedor> cadastrarRecebedor(Recebedor novoRecebedor) {
		Optional<Recebedor> recebedorExistente = repositoryRecebedor.findByEmail(novoRecebedor.getEmail());
		if (recebedorExistente.isPresent()) {
			return Optional.empty();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(novoRecebedor.getSenha());
		novoRecebedor.setSenha(senhaCriptografada);
		return Optional.ofNullable(repositoryRecebedor.save(novoRecebedor));
	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Recebedor> usuarioPresente = repositoryRecebedor.findByEmail(usuarioLogin.get().getEmail());

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

	public Comunidade inscreverComunidade(Comunidade novaInscricao, Recebedor recebedor) {

		Comunidade comunidadeExistente = repositoryComunidade.findByNome(novaInscricao.getNome());

		Optional<Recebedor> recebedorExistente = repositoryRecebedor.findById(recebedor.getId());

		if (!recebedorExistente.isEmpty()) {
			comunidadeExistente.setRecebedor(repositoryRecebedor.findById(recebedor.getId()));

			return repositoryComunidade.save(comunidadeExistente);
		}
		return null;
	}

	public Optional<Recebedor> visualizarPerfil(String recebedor) {

		Optional<Recebedor> usuarioExistente = repositoryRecebedor.findByRecebedor(recebedor);
		if (usuarioExistente.isPresent()) {
			return usuarioExistente;
		} else {
			return Optional.empty();
		}

	}
}
