package br.com.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.academia.domain.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Integer>{
	
	@Query("Select ex from Exercicio ex WHERE ex.grupo.id = :grupo_id")
	public List<Exercicio> findExercicioByGrupo(@Param("grupo_id") Integer grupo_id);

	public List<Exercicio> findByNomeContainingIgnoreCase(String nome);
}
