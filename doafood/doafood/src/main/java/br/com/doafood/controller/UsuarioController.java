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
import br.com.doafood.model.Comunidade;
import br.com.doafood.model.Usuario;
import br.com.doafood.model.UsuarioLogin;
import br.com.doafood.repository.UsuarioRepository;
import br.com.doafood.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/usuario")
public class UsuarioController {
	
	private @Autowired UsuarioRepository repositoryUsuario;
	private @Autowired UsuarioService serviceUsuario;

	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario novoUsuario){
		Optional<Usuario> dto = serviceUsuario.cadastrarUsuario(novoUsuario);
		return !dto.isEmpty() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
		
		
	}
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> auth(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return serviceUsuario.logar(usuarioLogin)
				.map(usuario -> ResponseEntity.ok(usuario))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	
	@PostMapping("/inscrever/{idUsuario}/{idComunidade}")
		
	public ResponseEntity<?> inscreverComunidade (@PathVariable (value = "idComunidade") Long idComunidade, @PathVariable (value = "idUsuario") Long idUsuario){
		Optional<Usuario> dto = serviceUsuario.inscreverComunidade(idComunidade, idUsuario);
		return !dto.isEmpty() ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("/cadastrarComunidade/{idUsuario}")
	public ResponseEntity<?> cadastrarComunidade(
			@PathVariable (value = "idUsuario") Long idUsuario,
			@Valid @RequestBody Comunidade novaComunidade){
		Comunidade cadastrarComunidade = serviceUsuario.cadastrarComunidade(novaComunidade , idUsuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarComunidade);
	}
	
	@GetMapping
	private ResponseEntity<List<Usuario>> findAll(){
		return ResponseEntity.ok(repositoryUsuario.findAll());
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Usuario> findById(@PathVariable long id){
		return repositoryUsuario.findById(id).map(resp -> ResponseEntity.ok(resp)).
				orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")

	private ResponseEntity<List<Usuario>> findByNome(@PathVariable String nome){
		return ResponseEntity.ok(repositoryUsuario.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity <Usuario> put (@RequestBody Usuario usuario){
		return ResponseEntity.ok(repositoryUsuario.save(usuario));
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id){
		repositoryUsuario.deleteById(id);
	}
	
	@DeleteMapping("/comunidade/delete/{id_comunidade}/{cnpj}")
    public ResponseEntity<?> deletarComunidade(
            @PathVariable(value = "cnpj") String usuarioCnpj,
            @PathVariable(value = "id_comunidade") Long idComunidade){
        Optional<Usuario> retorno = serviceUsuario.deletarComunidade(idComunidade, usuarioCnpj);
        if(retorno == null) {
            return new ResponseEntity<String>("Comunidade ou Cnpj inválido", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Usuario>(HttpStatus.ACCEPTED);
    }
}
