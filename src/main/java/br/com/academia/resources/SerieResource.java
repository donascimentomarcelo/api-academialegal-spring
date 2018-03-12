package br.com.academia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.Serie;
import br.com.academia.services.SerieService;

@RestController
@RequestMapping(value="/series")
public class SerieResource {
	
	@Autowired
	private SerieService serieService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Serie>> listPerPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Serie> list = serieService.findPage(page, linesPerPage, orderBy, direction);
	//	Page<UsuarioDTO> listDto = list.map(usuario -> new UsuarioDTO(usuario));
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping( value="/listByUser",method = RequestMethod.GET)
	public ResponseEntity<Page<Serie>> listPerPageByUser(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Serie> list = serieService.findPageByUser(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
