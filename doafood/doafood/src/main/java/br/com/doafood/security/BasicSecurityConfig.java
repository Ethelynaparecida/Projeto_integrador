package br.com.doafood.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

	private @Autowired UserDetailsService userdetailsService;
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(userdetailsService);
 	}

	@Bean
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		
 		http
 		.authorizeRequests()
 		.antMatchers("/**").permitAll() 
 		.antMatchers("/usuario/logar").permitAll()
 		.antMatchers("/usuario/cadastrar").permitAll()
 		//.antMatchers("/doador/logar").permitAll()
 		//.antMatchers("/doador/cadastrar").permitAll()
 		.antMatchers(HttpMethod.GET ,"/publicacao").permitAll()
		.antMatchers(HttpMethod.GET ,"/comunidade").permitAll()
 		.anyRequest().authenticated()
 		.and().httpBasic()
 		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 		.and().cors()
 		.and().csrf().disable();
	}
}
