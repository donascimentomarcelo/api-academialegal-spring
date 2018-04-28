package br.com.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Usuario;
import br.com.academia.repositories.UsuarioRepository;
import br.com.academia.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		
		Usuario usuario =  usuarioRepository.findByEmail(email);
		
		if(usuario == null)
		{
			throw new UsernameNotFoundException(email);
		}

		return new UserSpringSecurity(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getPerfis());
	}
	
}
