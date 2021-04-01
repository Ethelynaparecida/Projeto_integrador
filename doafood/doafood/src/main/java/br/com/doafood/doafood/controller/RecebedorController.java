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

import br.com.doafood.doafood.model.Recebedor;
import br.com.doafood.doafood.repository.RecebedorRepository;

@RequestMapping("/recebedor")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class RecebedorController {

	

		@Autowired
		private RecebedorRepository repository;

		@GetMapping
		public ResponseEntity<List<Recebedor>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Recebedor> getById(@PathVariable long id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}
		
		
		@GetMapping("/email/{email}")
		private ResponseEntity<List<Recebedor>> findByEmailCategoria(@PathVariable String email){
			return ResponseEntity.ok(repository.findByEmailContainingIgnoreCase(email));
		}
		@GetMapping("/cidade/{cidade}")
		private ResponseEntity<List<Recebedor>> findByCidadeCategoria(@PathVariable String cidade){
			return ResponseEntity.ok(repository.findByCidadeContainingIgnoreCase(cidade));
		}

		@PostMapping
		public ResponseEntity<Recebedor> post(@RequestBody Recebedor id) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(id));
		}

		@PutMapping
		public ResponseEntity<Recebedor> put(@RequestBody Recebedor id) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(id));
		}

		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
		}

	}