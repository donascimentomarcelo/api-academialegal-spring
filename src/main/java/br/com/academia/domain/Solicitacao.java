package br.com.academia.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.domain.enums.TipoSerie;

@Entity
public class Solicitacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqSolicitacao")
	@SequenceGenerator(name = "seqSolicitacao", sequenceName = "seq_id_solicitacao")
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataSolicitacao;
	private Integer tipoSerie;
	private Integer statusSerie;
	private String descricao;
	private String justificativa;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="solicitacao")
	@JsonIgnore
	private Serie serie;
	
	public Solicitacao() {
		super();
	}


	public Solicitacao(Integer id, Date dataSolicitacao, TipoSerie tipoSerie, StatusSerie statusSerie, String descricao,String justificativa, Usuario usuario) {
		super();
		this.id = id;
		this.dataSolicitacao = dataSolicitacao;
		this.tipoSerie = (tipoSerie == null) ? null : tipoSerie.getCodigo();
		this.statusSerie = (statusSerie == null) ? null : statusSerie.getCodigo();
		this.descricao = descricao;
		this.justificativa = justificativa;
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

	
	public StatusSerie getStatusSerie() {
		return StatusSerie.toEnum(statusSerie);
	}


	public void setStatusSerie(StatusSerie statusSerie) {
		this.statusSerie = statusSerie.getCodigo();
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
	
	
	public Serie getSerie() {
		return serie;
	}

	
	public void setSerie(Serie serie) {
		this.serie = serie;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solicitacao other = (Solicitacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
