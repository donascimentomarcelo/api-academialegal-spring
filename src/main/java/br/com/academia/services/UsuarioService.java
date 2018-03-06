package br.com.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Usuario;
import br.com.academia.domain.dto.UsuarioDTO;
import br.com.academia.exceptions.ObjectNotFoundException;
import br.com.academia.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Usuario fromDTO(UsuarioDTO dto) 
	{
		return new Usuario(dto.getId(), dto.getEmail(), dto.getNome(), bCryptPasswordEncoder.encode(dto.getSenha()));
	}

	public Usuario save(Usuario usuario)
	{
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return usuarioRepository.findAll(pageRequest);
	}

	public Usuario find(Integer id) {
		
		Usuario usuario = usuarioRepository.findOne(id);
		
		if(usuario == null)
		{
			throw new ObjectNotFoundException("O código "+ id +" não foi encontrado!");
		}
		
		return usuario;
	}

	public Usuario update(Usuario usuario, Integer id) {
		
		find(id);
		usuario.setId(id);
		
		return usuarioRepository.save(usuario);
	}

	public Usuario findByEmail(String email) {
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null)
		{
			throw new ObjectNotFoundException("O e-mail "+ email +" não foi encontrado!");
		}
		
		return usuario;
	}
	
}
