package br.com.doafood.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.doafood.model.Doador;
import br.com.doafood.model.Recebedor;
import br.com.doafood.repository.DoadorRepository;
import br.com.doafood.repository.RecebedorRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private @Autowired DoadorRepository repositoryDoador;
	private @Autowired RecebedorRepository repositoryRecebedor;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		Optional<Doador> doador = repositoryDoador.findByEmail(userName);
		doador.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
	
		return doador.map(UserDetailsImpl::new).get();
}
		
}
