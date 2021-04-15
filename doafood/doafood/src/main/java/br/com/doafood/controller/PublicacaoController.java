package br.com.doafood.controller;

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

import br.com.doafood.model.Publicacao;
import br.com.doafood.repository.PublicacaoRepository;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/publicacao")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PublicacaoController {




		@Autowired
		private PublicacaoRepository repository;
		
		@ApiOperation(value="Retorna uma lista de publicação")
		@GetMapping
		public ResponseEntity<List<Publicacao>> getAll() {
			return ResponseEntity.ok(repository.findAll());
		}

		@GetMapping("/{id}")
		@ApiOperation(value="Busca e retorna a publicação pelo id")
		public ResponseEntity<Publicacao> getById(@PathVariable long id) {
			return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
		}
		
		

		/*@PostMapping
		@ApiOperation(value="Busca e retorna a peblicação pela lista de id")
		public ResponseEntity<Publicacao> post(@RequestBody Publicacao id) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(id));
		}

		@PutMapping
		@ApiOperation(value="Busca e retorna a peblicação pela lista de id")
		public ResponseEntity<Publicacao> put(@RequestBody Publicacao id) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(id));
		}

		@DeleteMapping("/{id}")
		@ApiOperation(value="Busca e retorna a peblicação pela lista de id")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
		}*/

	}

