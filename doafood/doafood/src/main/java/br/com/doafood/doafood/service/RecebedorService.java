package br.com.doafood.doafood.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.model.Recebedor;
import br.com.doafood.doafood.repository.PublicacaoRepository;
import br.com.doafood.doafood.repository.RecebedorRepository;

@Service
public class RecebedorService {
	@Autowired
	private RecebedorRepository repository;
	private PublicacaoRepository repositoryP;
	
	public Optional<Recebedor> cadastrarRecebedor (Recebedor newrecebedor) {
	Optional<Recebedor> recebedorExistente = repository.findByEmail(newrecebedor.getEmail());
	if(recebedorExistente.isPresent()) {
		return null;
		}
	Optional<Recebedor> recebedorCadastrado = Optional.ofNullable(repository.save(newrecebedor));
	if (recebedorCadastrado.isPresent()) {
		return recebedorCadastrado;
	} else {
		return Optional.empty();
	}
	}
public Optional<Comunidade> criarComunidade(Comunidade novaComunidade, Long idRecebedor){
		
		Optional<Recebedor> usuarioExistente = repository.findById(idRecebedor);
		if (usuarioExistente.isPresent()) {
			Comunidade ComunidadeCadastrado = repository.save(novaComunidade);
			ComunidadeCadastrado. setRecebedor (usuarioExistente.get());
			return Optional.ofNullable(repository.save(ComunidadeCadastrado));
		} 
		else {
			return Optional.empty();
			}
public Optional<Recebedor> visualizarPerfil(String recebedor){
	
	Optional<Recebedor> usuarioExistente = repository.findByRecebedor(recebedor);
	if (usuarioExistente.isPresent()) {
		return usuarioExistente;
	} 
	else {
		return Optional.empty();
		}
	


