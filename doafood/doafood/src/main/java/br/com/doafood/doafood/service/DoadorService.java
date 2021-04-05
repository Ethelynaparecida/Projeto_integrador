package br.com.doafood.doafood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.model.Doador;
import br.com.doafood.doafood.repository.ComunidadeRepository;
import br.com.doafood.doafood.repository.DoadorRepository;

@Service
public class DoadorService {

	@Autowired
	private DoadorRepository repositoryDoador;
	private ComunidadeRepository repositoryComunidade;

	public Doador cadastrarDoador(Doador novoDoador) {
		Optional<Doador> doadorExistente = repositoryDoador.findById(novoDoador.getId());

		if (doadorExistente.isPresent()) {
			return null;
		}
		return repositoryDoador.save(novoDoador);
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

	public Optional<Doador> logarDoador(Optional<Doador> Doador) {
		Doador doadorExistente = repositoryDoador.findByEmail(Doador.get().getEmail());

		if (doadorExistente.isPresent()) {
			Doador.get().getEmail();
			Doador.get().getSenha();
			
			return Doador;
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
