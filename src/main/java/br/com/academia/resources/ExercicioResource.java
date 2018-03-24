package br.com.academia.resources;

import java.net.URI;
import java.util.List;

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
	public ResponseEntity<Page<ExercicioDTO>> list(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Exercicio> list = exercicioService.findPage(page, linesPerPage, orderBy, direction);
		Page<ExercicioDTO> listDto = list.map(exercicio -> new ExercicioDTO(exercicio));
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody ExercicioNewDTO exercicioNweDto)
	{
		Exercicio exercicio = exercicioService.fromDTO(exercicioNweDto);
		
		exercicio = exercicioService.save(exercicio);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(exercicio.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ExercicioDTO dto, @PathVariable Integer id)
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
	
	@RequestMapping(value="/name", method = RequestMethod.GET)
	public ResponseEntity<List<Exercicio>> findByName(@RequestParam(value="name") String nome)
	{
		List<Exercicio> exercicio = exercicioService.findByName(nome);
		
		return ResponseEntity.ok().body(exercicio);
	}

}
