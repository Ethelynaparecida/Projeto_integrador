package br.com.doafood.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.doafood.model.Usuario;
import br.com.doafood.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private @Autowired UsuarioRepository repositoryUsuario;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Usuario> usuario = repositoryUsuario.findByEmail(userName);
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
	
		return usuario.map(UserDetailsImpl::new).get();
}
		
}
