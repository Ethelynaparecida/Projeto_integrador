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
	
	public Optional<Publicacao> criarPublicacao(Long idComunidade, Publicacao publicacao) {
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);
		if(comunidadeExistente.isPresent()) {
			publicacao.setPubliComunidade(comunidadeExistente.get());		
			return Optional.ofNullable(repositoryPublicacao.save(publicacao));
		}
		return Optional.empty();
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
