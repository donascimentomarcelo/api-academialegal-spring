package br.com.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Grupo;
import br.com.academia.repositories.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> list()
	{
		return grupoRepository.findAll();
	}

	public Grupo save(Grupo grupo) {
		grupo.setId(null);
		return grupoRepository.saveAndFlush(grupo);
	}

}
