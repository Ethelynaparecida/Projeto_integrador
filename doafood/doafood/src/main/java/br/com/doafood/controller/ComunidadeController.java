package br.com.doafood.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.doafood.model.Comunidade;
import br.com.doafood.model.Publicacao;
import br.com.doafood.repository.ComunidadeRepository;
import br.com.doafood.service.ComunidadeService;


@RestController
@RequestMapping("/comunidade")
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ComunidadeController {
	@Autowired
	private ComunidadeRepository repository;
	private @Autowired ComunidadeService serviceComunidade;
	
	@GetMapping
	private ResponseEntity<List<Comunidade>> findAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Comunidade> findByIDCategoria(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	private ResponseEntity<List<Comunidade>> findByDescricaoCategoria(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping("/inserirPublicacao/{idComunidade}")
	public ResponseEntity <Publicacao> postCategoria (@RequestBody Publicacao novaPublicacao,
			@PathVariable (value = "idComunidade") Long idComunidade){
		Optional<Publicacao> postCategoria = serviceComunidade.criarPublicacao(idComunidade, novaPublicacao);
		return !postCategoria.isEmpty() ? ResponseEntity.ok(postCategoria.get()) : ResponseEntity.notFound().build();
	}
		
	@PutMapping
	public ResponseEntity <Comunidade> putCategoria (@RequestBody Comunidade categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id){
		repository.deleteById(id);
	}
}

