package br.com.academia.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.dto.ExercicioDTO;
import br.com.academia.domain.dto.ExercicioNewDTO;
import br.com.academia.services.ExercicioService;

@RestController
@RequestMapping(value = "/exercicios")
public class ExercicioResource {
	
	@Autowired
	private ExercicioService exercicioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ExercicioDTO>> list()
	{
		List<Exercicio> list = exercicioService.list();
		List<ExercicioDTO> listDto = list.stream().map(exercicio -> new ExercicioDTO(exercicio)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody ExercicioNewDTO exercicioNweDto)
	{
		Exercicio exercicio = exercicioService.fromDTO(exercicioNweDto);
		
		exercicio = exercicioService.save(exercicio);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(exercicio.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ExercicioDTO dto, @PathVariable Integer id)
	{
		Exercicio exercicio = exercicioService.fromDTO(dto);
		
		exercicio.setId(id);
		exercicioService.update(exercicio);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Exercicio> findOne(@PathVariable Integer id)
	{
		Exercicio exercicio = exercicioService.find(id);
		
		return ResponseEntity.ok().body(exercicio);
	}
}
