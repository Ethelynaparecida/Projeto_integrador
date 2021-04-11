package br.com.doafood.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
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
import br.com.doafood.model.Doador;
import br.com.doafood.model.UsuarioLogin;
import br.com.doafood.repository.DoadorRepository;
import br.com.doafood.service.DoadorService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/doador")

public class DoadorController {
	
	private @Autowired DoadorRepository repository;
	private @Autowired DoadorService serviceUsuario;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarDoador(@Valid @RequestBody Doador novoDoador){
		Optional<Doador> dto = serviceUsuario.cadastrarDoador(novoDoador);
		return !dto.isEmpty() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> auth(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return serviceUsuario.logar(usuarioLogin)
				.map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@GetMapping
	private ResponseEntity<List<Doador>> findAllCategoria(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Doador> findByIDCategoria(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/bairro/{bairro}")

	private ResponseEntity<List<Doador>> findByDescricaoCategoria(@PathVariable String bairro){

		return ResponseEntity.ok(repository.findByBairroContainingIgnoreCase(bairro));

	}

	private ResponseEntity<List<Doador>> findByBairro(@PathVariable String bairro){

		return ResponseEntity.ok(repository.findByBairroContainingIgnoreCase(bairro));




	}
	
	@PostMapping
	public ResponseEntity <Doador> postCategoria (@RequestBody Doador categoria){
		return ResponseEntity.status(HttpStatus.CREATED).
				body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity <Doador> putCategoria (@RequestBody Doador categoria){
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id){
		repository.deleteById(id);
	}
}
