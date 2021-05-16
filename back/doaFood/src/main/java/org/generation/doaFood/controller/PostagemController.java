package org.generation.doaFood.controller;

import java.util.List;
import java.util.Optional;

import org.generation.doaFood.model.Postagem;
import org.generation.doaFood.repository.PostagemRepository;
import org.generation.doaFood.service.UsuarioService;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins ="*",allowedHeaders="*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private UsuarioService userRepos;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById (@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tituloPostagem/{categoria}")
    public ResponseEntity<Object> GetByTitulo(@PathVariable(value = "categoria") String categoria){
        List<Postagem> lista = repository.findAllByCategoriaContainingIgnoreCase(categoria);

        if (lista.isEmpty()) {
            return  ResponseEntity.badRequest().body("Categoria inesistente!");
        } else {
            return ResponseEntity.ok(lista);
        }
    }
	
	@PostMapping("/inscrever")	
	@ApiOperation(value="Retorna usuario inscrito")
	public ResponseEntity<?> inscreverComunidade (
			 Long idUsuario,
			 Long idPostagem){
		Optional<Postagem> dto = userRepos.inscreverComunidade(idPostagem, idUsuario);
		return !dto.isEmpty() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
		
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
			
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
		
	@PutMapping
		
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
			
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
