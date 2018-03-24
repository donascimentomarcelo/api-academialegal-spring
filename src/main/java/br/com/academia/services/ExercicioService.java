package br.com.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.Grupo;
import br.com.academia.domain.dto.ExercicioDTO;
import br.com.academia.domain.dto.ExercicioNewDTO;
import br.com.academia.exceptions.ObjectNotFoundException;
import br.com.academia.repositories.ExercicioRepository;
import br.com.academia.repositories.GrupoRepository;

@Service
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Exercicio> list()
	{
		return exercicioRepository.findAll();
	}

	public Exercicio save(Exercicio exercicio) {
		
		Grupo grupo = grupoRepository.findOne(exercicio.getGrupo().getId());
		
		grupo.getExercicios().add(exercicio);
		
		return exercicioRepository.save(exercicio);
	}
	
	public Exercicio fromDTO(ExercicioDTO dto)
	{
		return new Exercicio(dto.getId(), dto.getNome(), null);
	}
	
	public Exercicio fromDTO(ExercicioNewDTO dto)
	{
		Grupo grupo = grupoRepository.findOne(dto.getGrupoId());
		
		if(grupo == null)
		{
			throw new ObjectNotFoundException("Violação de FK, código: " + dto.getGrupoId());
		}
		
		Exercicio exercicio = new Exercicio(null, dto.getNome(), grupo);
		
		grupo.getExercicios().add(exercicio);
		
		return exercicio;
	}

	public Exercicio update(Exercicio exercicio) 
	{
		Exercicio NewExercicio = find(exercicio.getId());
		
		NewExercicio.setNome(exercicio.getNome());
		
		return exercicioRepository.save(NewExercicio);
	}
	
	public Exercicio find(Integer id)
	{
		Exercicio exercicio = exercicioRepository.findOne(id);
		
		if(exercicio == null)
		{
			throw new ObjectNotFoundException("O código " + id +" não foi encontrado!");
		}
		
		return exercicio;
	}
	
	public List<Exercicio> findExercicioByGrupo(Integer id) {
		
		List<Exercicio> exercicio = exercicioRepository.findExercicioByGrupo(id);
		
		if(exercicio.isEmpty())
		{
			throw new ObjectNotFoundException("O código " + id +" não foi encontrado!");
		}
		
		return exercicio;
	}

	public Page<Exercicio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page,  linesPerPage, Direction.valueOf(direction), orderBy);
		return exercicioRepository.findAll(pageRequest);
	}

	public List<Exercicio> findByName(String nome) {
		
		List<Exercicio> exercicio = exercicioRepository.findByNomeContainingIgnoreCase(nome);
		
		if(exercicio.isEmpty())
		{
			throw new ObjectNotFoundException("Não existe exercicio com esse(s) caracteres: " + nome);
		}
		
		return exercicio;
	}
}
