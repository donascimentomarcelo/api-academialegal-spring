package br.com.academia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Serie;
import br.com.academia.repositories.SerieRepository;

@Service
public class SerieService {

	@Autowired
	private SerieRepository serieRepository;
	
	public Page<Serie> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) 
	{
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return serieRepository.findAll(pageRequest);
	}
}
