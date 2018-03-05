package br.com.academia.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Grupo;
import br.com.academia.services.GrupoService;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoService grupoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Grupo>> list()
	{
		List<Grupo> grupo = grupoService.list();
		return ResponseEntity.ok().body(grupo);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Grupo grupo)
	{
		grupo = grupoService.save(grupo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(grupo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
