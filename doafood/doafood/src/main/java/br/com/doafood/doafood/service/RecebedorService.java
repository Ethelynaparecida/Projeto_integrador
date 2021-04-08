
package br.com.doafood.doafood.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.model.Recebedor;
import br.com.doafood.doafood.repository.ComunidadeRepository;

import br.com.doafood.doafood.repository.RecebedorRepository;

@Service
public class RecebedorService {
	@Autowired
	private RecebedorRepository repository;
	private ComunidadeRepository repositoryComunidade;

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

	public Comunidade inscreverComunidade(Comunidade novaInscricao, Recebedor recebedor) {
		
		Comunidade comunidadeExistente = repositoryComunidade.findByNome(novaInscricao.getNome());

		Optional<Recebedor> recebedorExistente = repository.findById(recebedor.getId());
		
		if (!recebedorExistente.isEmpty()) {
			comunidadeExistente.setRecebedor(repository.findById(recebedor.getId()));
			return repositoryComunidade.save(comunidadeExistente);
		}
		return null;
	}

	public Optional<Recebedor> visualizarPerfil(String recebedor){
	
		Optional<Recebedor> usuarioExistente = repository.findByRecebedor(recebedor);
		if (usuarioExistente.isPresent()) {
			return usuarioExistente;
			}else {
				return Optional.empty();
		}
		

	}
}
