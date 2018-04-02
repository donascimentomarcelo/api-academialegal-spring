package br.com.academia.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.academia.domain.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Integer>{

	@Query("Select sr from Serie sr INNER JOIN sr.solicitacao sol INNER JOIN sol.usuario WHERE sol.usuario.id = :usuario_logado")
	Page<Serie> findByUserLogged(Pageable pageRequest, @Param("usuario_logado") Integer id);

	@Query("Select sr from Serie sr INNER JOIN sr.solicitacao sol WHERE lower(sol.solicitante) LIKE lower(concat('%', :nome ,'%'))")
	List<Serie> findBySolicitante(@Param("nome") String nome);

}
