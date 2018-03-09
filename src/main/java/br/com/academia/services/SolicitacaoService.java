package br.com.academia.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.dto.SolicitacaoDTO;
import br.com.academia.exceptions.ObjectNotFoundException;
import br.com.academia.repositories.SolicitacaoRepository;
import br.com.academia.security.UserSpringSecurity;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	public Page<Solicitacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return solicitacaoRepository.findAll(pageRequest);
	}

	public List<Solicitacao> findByUser() {
		
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		List<Solicitacao> solicitacao = solicitacaoRepository.findByUser(usuarioLogado.getId());
		
		return solicitacao;
	}
	
	public Solicitacao find(Integer id) {
		
		Solicitacao solicitacao = solicitacaoRepository.findOne(id);
		
		if(solicitacao == null)
		{
			throw new ObjectNotFoundException("O código " + id +" não foi encontrado!");
		}
		
		return solicitacao;
	}

	public Solicitacao fromDTO(SolicitacaoDTO dto) {
		return new Solicitacao(dto.getId(), dto.getDataSolicitacao(), dto.getTipoSerie(), dto.getStatusSerie(), dto.getDescricao(), dto.getJustificativa(), dto.getUsuario());
	}

	public Solicitacao save(Solicitacao solicitacao) {
		
		solicitacao.setDataSolicitacao(new Date());
		
		return solicitacaoRepository.save(solicitacao);
	}
	

}
