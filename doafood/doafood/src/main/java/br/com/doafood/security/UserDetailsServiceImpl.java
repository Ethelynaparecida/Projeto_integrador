package br.com.doafood.security;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.doafood.doafood.model.Doador;
import br.com.doafood.doafood.repository.DoadorRepository;
import br.com.doafood.doafood.repository.RecebedorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private @Autowired DoadorRepository repositoryDoador;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Doador> doador = repositoryDoador.findByUsuario(userName);
		doador.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
	
		return doador.map(UserDetailsImpl::new).get();
}
	
	
}
