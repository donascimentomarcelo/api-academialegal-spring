package br.com.academia.resources;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.Grupo;
import br.com.academia.domain.dto.GrupoDTO;
import br.com.academia.services.ExercicioService;
import br.com.academia.services.GrupoService;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoService grupoService;
	@Autowired
	private ExercicioService exercicioService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Grupo>> list()
	{
		List<Grupo> grupo = grupoService.list();
		return ResponseEntity.ok().body(grupo);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Grupo grupo)
	{
		grupo = grupoService.save(grupo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(grupo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/exercicios")
	public ResponseEntity<List<Exercicio>> findExercicioByGrupo(@PathVariable Integer id)
	{
		List<Exercicio> exercicio = exercicioService.findExercicioByGrupo(id);
		
		return ResponseEntity.ok().body(exercicio);
	}
	
	@RequestMapping(value = "/{id}/grupoByExercicio", method = RequestMethod.GET)
	public ResponseEntity<GrupoDTO> findOneGrupoExercicio(@PathVariable Integer id)
	{
		Grupo grupo = grupoService.findOneGrupoExercicio(id);
		
		ModelMapper modelMapper = new ModelMapper();
		
		GrupoDTO dto = modelMapper.map(grupo, GrupoDTO.class);
		
		return ResponseEntity.ok().body(dto);
	}
}
