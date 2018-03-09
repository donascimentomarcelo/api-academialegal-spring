package br.com.academia.domain.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.Usuario;
import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.domain.enums.TipoSerie;

public class SolicitacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dataSolicitacao;
	private TipoSerie tipoSerie;
	private StatusSerie statusSerie;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=10, max=80, message="O tamanho deve ser entre 10 e 80 caracteres")
	private String descricao;
	private String justificativa;
	@JsonIgnore
	private Usuario usuario;
	
	public SolicitacaoDTO() {
		super();
	}
	
	

	public SolicitacaoDTO(Solicitacao solicitacao) {
		id = solicitacao.getId();
		dataSolicitacao = solicitacao.getDataSolicitacao();
		tipoSerie = solicitacao.getTipoSerie();
		statusSerie = solicitacao.getStatusSerie();
		descricao = solicitacao.getDescricao();
		justificativa = solicitacao.getJustificativa();
		usuario = solicitacao.getUsuario();
	
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

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
