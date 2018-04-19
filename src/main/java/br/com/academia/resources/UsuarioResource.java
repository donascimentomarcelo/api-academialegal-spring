package br.com.academia.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Usuario;
import br.com.academia.domain.dto.PerfilDTO;
import br.com.academia.domain.dto.UsuarioDTO;
import br.com.academia.domain.dto.UsuarioListDTO;
import br.com.academia.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioListDTO>> listPerPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Usuario> list = usuarioService.findPage(page, linesPerPage, orderBy, direction);
		Page<UsuarioListDTO> listDto = list.map(usuario -> new UsuarioListDTO(usuario));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody UsuarioDTO dto)
	{
		Usuario usuario = usuarioService.fromDTO(dto);
		
		usuario = usuarioService.save(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UsuarioDTO dto, @PathVariable Integer id)
	{
		Usuario usuario = usuarioService.fromDTO(dto);
		
		usuario = usuarioService.update(usuario, id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id)
	{
		Usuario usuario = usuarioService.find(id);
		
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Usuario> findByEmail(@RequestParam(value="email") String email)
	{
		Usuario usuario = usuarioService.findByEmail(email);
				
		return ResponseEntity.ok().body(usuario);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}/addPerfil", method = RequestMethod.PUT)
	public ResponseEntity<Void> addPerfil(@RequestBody PerfilDTO dto, @PathVariable Integer id)
	{
		Usuario usuario = usuarioService.fromDTOAddPerfil(dto, id);
		
		usuario = usuarioService.setPerfil(usuario, id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}/removePerfil", method = RequestMethod.PUT)
	public ResponseEntity<Void> removePerfil(@RequestBody PerfilDTO dto, @PathVariable Integer id)
	{
		Usuario usuario = usuarioService.fromDTORemovePerfil(dto, id);
		
		usuario = usuarioService.setPerfil(usuario, id);
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/picture",method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile multipartFile)
	{
		URI uri = usuarioService.uploadProfilePicture(multipartFile);

		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/name", method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioListDTO>> findByName(@RequestParam(value="name") String nome)
	{
		List<Usuario> list = usuarioService.findByName(nome);
		
		List<UsuarioListDTO> listDto = list.stream().map(usuario -> new UsuarioListDTO(usuario)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> dashboard()
	{
		List<Usuario> usuarios = usuarioService.dashboard();
		
		return ResponseEntity.ok().body(usuarios);
	}

}
