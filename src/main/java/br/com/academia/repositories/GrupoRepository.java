package br.com.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.academia.domain.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
	
	@Query("Select grp from Grupo grp INNER JOIN grp.exercicios ex where ex.id = :exercicio_id")
	public Grupo findOneGrupoByExercicio(@Param("exercicio_id") Integer id);
}
