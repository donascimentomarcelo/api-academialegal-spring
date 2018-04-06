package br.com.academia.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.Usuario;
import br.com.academia.domain.dto.RejeitarDTO;
import br.com.academia.domain.dto.SolicitacaoDTO;
import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.exceptions.DataIntegrityException;
import br.com.academia.exceptions.ObjectNotFoundException;
import br.com.academia.repositories.SolicitacaoRepository;
import br.com.academia.repositories.UsuarioRepository;
import br.com.academia.security.UserSpringSecurity;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		return new Solicitacao(dto.getId(), dto.getDataSolicitacao(), dto.getTipoSerie(), dto.getStatusSerie(), dto.getDescricao(), dto.getJustificativa(), dto.getUsuario(), null);
	}

	public Solicitacao save(Solicitacao solicitacao) {
		
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		Usuario usuario = usuarioRepository.findOne(usuarioLogado.getId());
		
		solicitacao.setDataSolicitacao(new Date());
		solicitacao.setSolicitante(usuarioLogado.getNome());
		solicitacao.setStatusSerie(StatusSerie.PENDENTE);
		solicitacao.setJustificativa(null);
		solicitacao.setUsuario(usuario);
		
		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao rejeitar(Integer id, String justificativa) {
		
		Solicitacao solicitacao = find(id);
		
		if(solicitacao.getStatusSerie() != StatusSerie.PENDENTE)
		{
			throw new DataIntegrityException("O registro já foi "+ solicitacao.getStatusSerie().getDescricao().toLowerCase()+" anteriormente");
		}
		
		solicitacao.setStatusSerie(StatusSerie.REJEITADO);
		solicitacao.setJustificativa(justificativa);
		
		solicitacao = solicitacaoRepository.save(solicitacao);
		
		return solicitacao;
	}

	public Solicitacao fromDTO(RejeitarDTO dto) {
		return new Solicitacao(dto.getId(), null, null, null, null, dto.getJustificativa(), null, null);
	}

	public Page<Solicitacao> findPageSolicitacaoPendente(Integer page, Integer linesPerPage, String orderBy,
			String direction, Integer status) {
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return solicitacaoRepository.findPageSolicitacaoPendente(pageRequest, status);
	}

	public List<Solicitacao> findBySolicitante(String nome) {
		
		List<Solicitacao> solicitacao = solicitacaoRepository.findBySolicitanteContainingIgnoreCaseOrderByDataSolicitacaoDesc(nome);
		
		return solicitacao;
	}	

}
