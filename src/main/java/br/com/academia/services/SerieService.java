package br.com.academia.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.ItemSerie;
import br.com.academia.domain.Serie;
import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.repositories.ItemSerieRepository;
import br.com.academia.repositories.SerieRepository;
import br.com.academia.repositories.SolicitacaoRepository;
import br.com.academia.resources.VencimentoService;
import br.com.academia.security.UserSpringSecurity;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;
	
	@Autowired
	private VencimentoService vencimentoService;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository; 
	
	@Autowired
	private ItemSerieRepository itemSerieRepository;
	
	@Autowired
	private PushService pushService;
	
	
	public Page<Serie> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return serieRepository.findAll(pageRequest);
	}

	public Page<Serie> findPageByUser(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		return serieRepository.findByUserLogged(pageRequest, usuarioLogado.getId());
	}

	public Serie save(Serie serie) {
		
		String message = "Sua série foi criada pelo(a) professor(a) ";
		
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		serie.setId(null);
		serie.setDataCriacao(new Date());
		vencimentoService.preencherDataVencimento(serie);
		serie.setProfessor(usuarioLogado.getNome());
		Solicitacao solicitacao = solicitacaoRepository.findOne(serie.getSolicitacao().getId());
		serie.setSolicitacao(solicitacao);
		serie.setTipoSerie(solicitacao.getTipoSerie());
		serie = serieRepository.save(serie);
		solicitacao.setStatusSerie(StatusSerie.CONCLUIDO);
		solicitacaoRepository.save(solicitacao);
		for(ItemSerie itemSerie: serie.getItens())
		{
			itemSerie.setSerie(serie);
		}
		
		itemSerieRepository.save(serie.getItens());
		
		pushService.push(solicitacao.getUsuario().getEmail(), usuarioLogado.getNome(), message);
		
		return serie;
	}

	public List<Serie> findBySolicitante(String nome) 
	{	
		List<Serie> list = serieRepository.findBySolicitante(nome);
		return list;
	}

	public Serie findOne(Integer id) 
	{
		Serie serie = serieRepository.findOne(id);
		return serie;
	}

	public List<Serie> myDashboard() {
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		List<Serie> serie = serieRepository.myDashboard(usuarioLogado.getId());
		return serie;
	}

	public List<Serie> dashboard() {
		List<Serie> serie = serieRepository.dashboard();
		return serie;
	}

}
