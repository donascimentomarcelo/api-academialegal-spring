package br.com.academia.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.academia.domain.Usuario;
import br.com.academia.domain.dto.PerfilDTO;
import br.com.academia.domain.dto.UsuarioDTO;
import br.com.academia.domain.enums.Perfil;
import br.com.academia.exceptions.AuthorizationException;
import br.com.academia.exceptions.ObjectNotFoundException;
import br.com.academia.repositories.UsuarioRepository;
import br.com.academia.security.UserSpringSecurity;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

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
		
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		if(usuarioLogado ==  null || !usuarioLogado.hasRole(Perfil.ADMIN) && !id.equals(usuarioLogado.getId()))
		{
			throw new AuthorizationException("Acesso negado");
		}
		
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
	
	public Usuario fromDTOAddPerfil(PerfilDTO dto, Integer id) 
	{
		Usuario usuario = find(id);
		
		if(dto.getPerfil() == 1)
		{
			usuario.addPerfil(Perfil.ADMIN);
		}
		else if(dto.getPerfil() == 3)
		{
			usuario.addPerfil(Perfil.PROFESSOR);
		}
		else
		{
			throw new ObjectNotFoundException("Identificador não localizado no sistema");
		}
		
		return usuario;
	}
	
	public Usuario fromDTORemovePerfil(PerfilDTO dto, Integer id) 
	{
		Usuario usuario = find(id);
		
		if(dto.getPerfil() == 1)
		{
			usuario.removePerfil(Perfil.ADMIN);
		}
		else if(dto.getPerfil() == 2)
		{
			usuario.removePerfil(Perfil.ALUNO);
		}
		else if(dto.getPerfil() == 3)
		{
			usuario.removePerfil(Perfil.PROFESSOR);
		}
		else
		{
			throw new ObjectNotFoundException("Identificador não localizado no sistema");
		}
		
		return usuario;
	}

	
	public Usuario setPerfil(Usuario usuario, Integer id) {
		
		usuario.setId(id);
		usuarioRepository.save(usuario);

		return usuario;
	}

	public URI uploadProfilePicture(MultipartFile multipartFile)
	{
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		if(usuarioLogado == null)
		{
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		
		jpgImage = imageService.cropSquare(jpgImage);
		
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + usuarioLogado.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

	public List<Usuario> findByName(String nome) {
		
		List<Usuario> usuario = usuarioRepository.findByNomeContainingIgnoreCase(nome);
		
		return usuario;
	}
}
