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

import br.com.academia.domain.Serie;
import br.com.academia.services.SerieService;

@RestController
@RequestMapping(value="/series")
public class SerieResource {
	
	@Autowired
	private SerieService serieService;
	
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Serie>> listPerPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "dataCriacao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC")String direction)
	{
		Page<Serie> list = serieService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping( value="/listByUser",method = RequestMethod.GET)
	public ResponseEntity<Page<Serie>> listPerPageByUser(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "dataCriacao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC")String direction)
	{
		Page<Serie> list = serieService.findPageByUser(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Serie> create(@Valid @RequestBody Serie serie)
	{
		serie = serieService.save(serie);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(serie.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@RequestMapping(value="/name", method = RequestMethod.GET)
	public ResponseEntity<List<Serie>> findBySolicitante(@RequestParam(value="name") String nome)
	{
		List<Serie> list = serieService.findBySolicitante(nome);
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)	
	public ResponseEntity<Serie> findOne(@PathVariable Integer id)
	{
		Serie serie = serieService.findOne(id);
		
		return ResponseEntity.ok().body(serie);
	}
	
	@RequestMapping(value="/myDashboard", method = RequestMethod.GET) 
	public ResponseEntity<List<Serie>> myDashboard()
	{
		List<Serie> serie = serieService.myDashboard();
		
		return ResponseEntity.ok().body(serie);
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET) 
	public ResponseEntity<List<Serie>> dashboard()
	{
		List<Serie> serie = serieService.dashboard();
		
		return ResponseEntity.ok().body(serie);
	}
}
