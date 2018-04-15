package br.com.academia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.academia.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Transactional(readOnly=true)
	Usuario findByEmail(String email);

	List<Usuario> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT new map(CASE "
			+ "WHEN (p = 1) THEN 'Admin' "
			+ "WHEN (p = 2) THEN 'Aluno' "
			+ "WHEN (p = 3) THEN 'Professor' END AS perfil, "
			+ "COUNT(u.id) AS qtddUsuario) "
			+ "FROM Usuario u "
			+ "INNER JOIN u.perfis p "
			+ "GROUP BY p")
	List<Usuario> dashboard();
	
	/*
	 * 	@Query("SELECT new map(CASE "
			+ "WHEN (p.codigo = 1) THEN 'Admin' "
			+ "WHEN (p.codigo = 2) THEN 'Aluno' "
			+ "WHEN (p.codigo = 3) THEN 'Professor' END AS perfil, "
			+ "COUNT(u.id) AS qtddUsuario) "
			+ "FROM Usuario u "
			+ "INNER JOIN u.perfis p "
			+ "GROUP BY p.codigo")
		List<Usuario> dashboard();
	 * */

}
