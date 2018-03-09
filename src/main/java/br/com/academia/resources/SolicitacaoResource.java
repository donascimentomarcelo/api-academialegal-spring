package br.com.academia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.dto.SolicitacaoDTO;
import br.com.academia.services.SolicitacaoService;

@RestController
@RequestMapping(value = "/solicitacoes")
public class SolicitacaoResource {

	@Autowired
	private SolicitacaoService solicitacaoService; 
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<SolicitacaoDTO>> listPerPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "dataSolicitacao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction)
	{
		Page<Solicitacao> list = solicitacaoService.findPage(page, linesPerPage, orderBy, direction);
		Page<SolicitacaoDTO> listDTO = list.map(solicitacao -> new SolicitacaoDTO(solicitacao));
		return ResponseEntity.ok().body(listDTO);
	}
}
