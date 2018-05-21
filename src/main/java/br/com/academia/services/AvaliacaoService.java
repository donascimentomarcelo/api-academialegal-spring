package br.com.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Avaliacao;
import br.com.academia.repositories.AvaliacaoRepository;
import br.com.academia.security.UserSpringSecurity;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	public Page<Avaliacao> list(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return avaliacaoRepository.findAll(pageRequest);
	}
	
	public List<Avaliacao> findByAluno(String name)
	{
		List<Avaliacao> avaliacao = avaliacaoRepository.findByAluno(name);
		return avaliacao;
	}
	
	public Avaliacao findByUserLogged()
	{
		UserSpringSecurity usuarioLogado = UserService.authenticated();
		
		Avaliacao avaliacao = avaliacaoRepository.findOne(usuarioLogado.getId());
		
		return avaliacao;
	}
	
	public Avaliacao create(Avaliacao avaliacao)
	{
		avaliacao = avaliacaoRepository.save(avaliacao);
		return avaliacao;
	}
}
