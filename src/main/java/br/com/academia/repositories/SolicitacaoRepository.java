package br.com.academia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academia.domain.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer>{

}
