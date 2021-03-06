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
import io.swagger.annotations.ApiOperation;


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
	@ApiOperation(value="Busca e retorna a comunidade pela listo id")
	private ResponseEntity<Comunidade> findById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	@ApiOperation(value="Busca e retorna a comunidade pela descrição")
	private ResponseEntity<List<Comunidade>> findByDescricao(@PathVariable String sobre){
		return ResponseEntity.ok(repository.findBySobreContainingIgnoreCase(sobre));
	}
	
	@PostMapping
	public ResponseEntity<Comunidade> post (@RequestBody Comunidade comunidade){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(comunidade));
	}
	
	/*@PostMapping
	@ApiOperation(value="Insere dados e retorna a publicação")
	public ResponseEntity <Comunidade> postPublicacao (@RequestBody Publicacao novaPublicacao, Long idComunidade){
		Optional<Publicacao> postPublicacao = serviceComunidade.criarPublicacao(idComunidade, novaPublicacao);
		return !postPublicacao.isEmpty() ? ResponseEntity.ok(postPublicacao.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ApiOperation(value="Busca e retorna a peblicação pela lista de id")
	public ResponseEntity<Publicacao> post(@RequestBody Publicacao id) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(id));
	}*/
		
	@PutMapping
	@ApiOperation(value="Modifica os dados da comunidade")
	public ResponseEntity <Comunidade> putComunidade (@RequestBody Comunidade comunidade){
		return ResponseEntity.ok(repository.save(comunidade));
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta comunidade pelo id")
	public void deleteComunidade(@PathVariable long id){
		repository.deleteById(id);
	}
}

