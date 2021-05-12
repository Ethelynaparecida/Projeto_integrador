package org.generation.doaFood.security;

import java.util.Optional;

import org.generation.doaFood.model.Usuario;
import org.generation.doaFood.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repositoryUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		Optional<Usuario> user = repositoryUsuario.findByEmail(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not Found.") );
		
		return user.map(UserDetailsImpl::new).get();
	}
	
	
}
