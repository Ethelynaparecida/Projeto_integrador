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

import br.com.doafood.doafood.model.Publicacao;
import br.com.doafood.doafood.repository.PublicacaoRepository;

@RequestMapping("/compra")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PublicacaoController {




		@Autowired
		private PublicacaoRepository repository;

		@GetMapping
		public ResponseEntity<List<Publicacao>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<Publicacao> getById(@PathVariable long id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}
		
		

		@PostMapping
		public ResponseEntity<Publicacao> post(@RequestBody Publicacao id) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(id));
		}

		@PutMapping
		public ResponseEntity<Publicacao> put(@RequestBody Publicacao id) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(id));
		}

		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
		}

	}

