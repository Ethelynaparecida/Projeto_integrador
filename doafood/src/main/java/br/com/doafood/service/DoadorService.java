package br.com.doafood.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.doafood.model.Comunidade;
import br.com.doafood.model.Doador;
import br.com.doafood.model.UsuarioLogin;
import br.com.doafood.repository.ComunidadeRepository;
import br.com.doafood.repository.DoadorRepository;

@Service
public class DoadorService {

	@Autowired
	private DoadorRepository repositoryDoador;
	private ComunidadeRepository repositoryComunidade;
	
	public Optional<Doador> cadastrarDoador(Doador novoDoador) {
		Optional<Doador> doadorExistente = repositoryDoador.findByEmail(novoDoador.getEmail());
		if (doadorExistente.isPresent()) {
			return Optional.empty();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(novoDoador.getSenha());
		novoDoador.setSenha(senhaCriptografada);
		return Optional.ofNullable(repositoryDoador.save(novoDoador));
	}
	//Apagarcomunidade
	
	public Optional<Doador> deletarComunidade(Long idDoador, Long idComunidade) {
		Optional<Doador> doadorExistente = repositoryDoador.findById(idDoador);
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);
		
		if(doadorExistente.isPresent()&&comunidadeExistente.isPresent()) {
			comunidadeExistente.get().setDoador(null);
			repositoryComunidade.save(comunidadeExistente.get());
			repositoryComunidade.deleteById(comunidadeExistente.get().getId());
			return repositoryDoador.findById(doadorExistente.get().getId());
		}
		return null;
	}
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> usuarioLogin){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Doador> usuarioPresente = repositoryDoador.findByEmail(usuarioLogin.get().getEmail());

		if(usuarioPresente.isPresent()) {
			if(encoder.matches(usuarioLogin.get().getSenha(), usuarioPresente.get().getSenha())) {
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
	//doador Criar Comunidade
	
	public Comunidade cadastrarComunidade (Comunidade novaComunidade, Long idDoador) {
		Comunidade comunidadeExistente = repositoryComunidade.save(novaComunidade);
		
		Optional<Doador> doadorExistente = repositoryDoador.findById(idDoador);
		if(doadorExistente.isPresent()) {
			comunidadeExistente.setDoador(doadorExistente.get());
			return repositoryComunidade.save(comunidadeExistente);
		}
		return null;
	}
	
	
	
}
