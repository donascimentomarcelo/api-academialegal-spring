package br.com.academia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.Grupo;
import br.com.academia.domain.dto.ExercicioDTO;
import br.com.academia.domain.dto.ExercicioNewDTO;
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
		
		Exercicio exercicio = new Exercicio(null, dto.getNome(), grupo);
		
		grupo.getExercicios().add(exercicio);
		
		return exercicio;
	}

	public Exercicio update(Exercicio exercicio) 
	{
		Exercicio NewExercicio = exercicioRepository.findOne(exercicio.getId());
		
		NewExercicio.setNome(exercicio.getNome());
		
		return exercicioRepository.save(NewExercicio);
	}
}
