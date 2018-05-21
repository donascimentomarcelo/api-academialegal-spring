package br.com.academia.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.academia.domain.Avaliacao;
import br.com.academia.services.AvaliacaoService;

@RestController
@RequestMapping(value="/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Avaliacao>> list(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "DESC")String direction)
	{
		Page<Avaliacao> list = avaliacaoService.list(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> findByAluno(@RequestParam("aluno") String name)
	{
		List<Avaliacao> list = avaliacaoService.findByAluno(name);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/userLogged", method = RequestMethod.GET)
	public ResponseEntity<Avaliacao> findByUserLogged()
	{
		Avaliacao avaliacao = avaliacaoService.findByUserLogged();
		return ResponseEntity.ok().body(avaliacao);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Avaliacao> create(@RequestBody Avaliacao avaliacao)
	{
		avaliacao = avaliacaoService.create(avaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(avaliacao.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
