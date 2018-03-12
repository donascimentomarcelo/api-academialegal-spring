package br.com.academia.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.domain.enums.TipoSerie;

public class SolicitacaoPendenteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataSolicitacao;
	private TipoSerie tipoSerie;
	private StatusSerie statusSerie;
	private String descricao;

	public SolicitacaoPendenteDTO() {
		super();
	}
	
	

	public SolicitacaoPendenteDTO(Solicitacao solicitacao) {
		id = solicitacao.getId();
		dataSolicitacao = solicitacao.getDataSolicitacao();
		tipoSerie = solicitacao.getTipoSerie();
		statusSerie = solicitacao.getStatusSerie();
		descricao = solicitacao.getDescricao();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public TipoSerie getTipoSerie() {
		return tipoSerie;
	}

	public void setTipoSerie(TipoSerie tipoSerie) {
		this.tipoSerie = tipoSerie;
	}

	public StatusSerie getStatusSerie() {
		return statusSerie;
	}

	public void setStatusSerie(StatusSerie statusSerie) {
		this.statusSerie = statusSerie;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
