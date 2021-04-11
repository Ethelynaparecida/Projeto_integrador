package br.com.doafood.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.doafood.model.Comunidade;
import br.com.doafood.model.Publicacao;
import br.com.doafood.repository.ComunidadeRepository;
import br.com.doafood.repository.PublicacaoRepository;

@Service
public class ComunidadeService {

	public @Autowired ComunidadeRepository repositoryComunidade;
	public @Autowired PublicacaoRepository repositoryPublicacao;
	
	public Publicacao criarPublicacao(Long idComunidade, Publicacao publicacao) {
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);
		Publicacao novaPublicacao = repositoryPublicacao.save(publicacao);
		if(comunidadeExistente.isPresent()) {
			novaPublicacao.setComunidade(comunidadeExistente.get());				
			return repositoryPublicacao.save(novaPublicacao);
		}
		return null;
	}
	
	public Optional<Comunidade> vizualizarComunidade(String comunidade){
	Optional<Comunidade> vizualizarComunidade = repositoryComunidade.findByNomeContainingIgnoreCase(comunidade);
	if (vizualizarComunidade.isPresent()) {
		return vizualizarComunidade;
		}else {
			return Optional.empty();
	}
	

}
}
