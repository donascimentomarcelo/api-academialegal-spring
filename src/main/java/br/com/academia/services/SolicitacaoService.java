package br.com.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Solicitacao;
import br.com.academia.repositories.SolicitacaoRepository;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public Page<Solicitacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return solicitacaoRepository.findAll(pageRequest);
	}
}
