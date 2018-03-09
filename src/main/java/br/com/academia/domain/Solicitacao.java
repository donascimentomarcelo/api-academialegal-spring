package br.com.academia.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.academia.domain.enums.TipoSerie;

@Entity
public class Solicitacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqSolicitacao")
	@SequenceGenerator(name = "seqSolicitacao", sequenceName = "seq_id_solicitacao")
	private Integer id;
	private Date dataSolicitacao;
	private Integer tipoSerie;
	private String descricao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	
	public Solicitacao() {
		super();
	}


	public Solicitacao(Integer id, Date dataSolicitacao, TipoSerie tipoSerie, String descricao, Usuario usuario) {
		super();
		this.id = id;
		this.dataSolicitacao = dataSolicitacao;
		this.tipoSerie = (tipoSerie == null) ? null : tipoSerie.getCodigo();
		this.descricao = descricao;
		this.usuario = usuario;
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
		return TipoSerie.toEnum(tipoSerie);
	}


	public void setTipoSerie(TipoSerie tipoSerie) {
		this.tipoSerie = tipoSerie.getCodigo();
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
