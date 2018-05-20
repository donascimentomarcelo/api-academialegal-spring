package br.com.academia.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Agenda;
import br.com.academia.domain.dto.AgendaDTO;
import br.com.academia.services.AgendaService;

@RequestMapping(value="/agenda")
@RestController
public class AgendaResource {
	
	@Autowired
	private AgendaService agendaService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<AgendaDTO>> list(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "startTime") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Agenda> list = agendaService.list(page, linesPerPage, orderBy, direction);
		Page<AgendaDTO> dto = list.map(x -> new AgendaDTO(x));
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ResponseEntity<?> listByDate(@RequestParam(value="startTime")  @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime)
	{
		List<Agenda> list = agendaService.listByDate(startTime);
		List<AgendaDTO> dto = list.stream().map(x -> new AgendaDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Agenda> create(@RequestBody Agenda agenda)
	{
		agenda = agendaService.create(agenda);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(agenda.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
