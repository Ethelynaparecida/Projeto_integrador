package br.com.doafood.doafood.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.doafood.doafood.model.Comunidade;
import br.com.doafood.doafood.repository.ComunidadeRepository;
import br.com.doafood.doafood.repository.PublicacaoRepository;

@Service
public class ComunidadeService {

	public @Autowired ComunidadeRepository repositoryComunidade;
	public @Autowired PublicacaoRepository repositoryPublicacao;
	
	/*public Comunidade criarPublicacao(Long idComunidade) {
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);
		
		if(comunidadeExistente.isPresent()) {
			comunidadeExistente.get().add(comunidadeExistente.get());
			return repositoryComunidade.save(comunidadeExistente.get());
		}
		return null;
		}
	public Publicacao inserirPublicacao(Publicacao novaPublicacao, Long idComunidade) {
		Optional<Comunidade> comunidadeExistente = repositoryComunidade.findById(idComunidade);
		if(comunidadeExistente.isPresent()) {
			novaPublicacao.get().;				
			novaPublicacao.get().setNome(novaPublicacao.get().getNome());
			novaPublicacao.get().setSenha(novaPublicacao.get().getSenha());
			
			repositoryPublicacao.save(novaPublicacao);
			return repositoryPublicacao.findById(idComunidade).get();
		}//Optional<Cliente> retorno = Optional.ofNullable(buscaCliente(cpf))
		return null;
	}*/
	
	public Optional<Comunidade> vizualizarComunidade(String comunidade){
	Optional<Comunidade> vizualizarComunidade = repositoryComunidade.findByNomeContainingIgnoreCase(comunidade);
	if (vizualizarComunidade.isPresent()) {
		return vizualizarComunidade;
		}else {
			return Optional.empty();
	}
	

}
}
