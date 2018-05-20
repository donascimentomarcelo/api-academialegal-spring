package br.com.academia.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.academia.domain.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer>{

	@Query(value = "select * from agenda a where date_trunc('day',a.start_time) = :date order by a.start_time desc", nativeQuery = true)
	List<Agenda> findByStartTime(@Param("date") Date startTime);
	
}
