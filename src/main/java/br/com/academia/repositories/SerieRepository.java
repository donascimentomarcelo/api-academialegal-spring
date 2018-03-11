package br.com.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.academia.domain.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer>{

}
