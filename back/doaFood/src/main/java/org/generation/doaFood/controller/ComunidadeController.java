package org.generation.doaFood.controller;

import java.util.List;

import org.generation.doaFood.model.Comunidade;
import org.generation.doaFood.repository.ComunidadeRepository;
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



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comunidade")
public class ComunidadeController {
	
	@Autowired
	private ComunidadeRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Comunidade>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comunidade> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Comunidade>> getByName(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Comunidade> post (@RequestBody Comunidade comunidade){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(comunidade));
	}

	@PutMapping
	public ResponseEntity<Comunidade> put (@RequestBody Comunidade comunidade){
		return ResponseEntity.ok(repository.save(comunidade));				
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
