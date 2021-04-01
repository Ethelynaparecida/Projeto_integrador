package br.com.doafood.doafood.controller;

import java.util.List;

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

import br.com.doafood.doafood.model.Doador;
import br.com.doafood.doafood.repository.DoadorRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/doador")

public class DoadorController {
	@Autowired
	private DoadorRepository repository;
	
	@GetMapping
	private ResponseEntity<List<Doador>> findAllCategoria(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Doador> findByIDCategoria(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/descricao/{descricao}")
	private ResponseEntity<List<Doador>> findByDescricaoCategoria(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findByBairroContainingIgnoreCase(descricao));
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
