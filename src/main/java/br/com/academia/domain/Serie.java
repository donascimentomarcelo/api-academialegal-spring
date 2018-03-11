package br.com.academia.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.academia.domain.enums.TipoSerie;

@Entity
public class Serie  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqSerie")
	@SequenceGenerator(name = "seqSerie", sequenceName = "seq_id_serie")
	private Integer id;
	
	private String observacao;
	private Date dataCriacao;
	private Date dataVencimento;
	private String professor;
	private Integer tipoSerie;
	
	@OneToMany(mappedBy = "id.serie")
	private Set<ItemSerie> itens = new HashSet<>();
	
	public Serie() {
		super();
	}


	public Serie(Integer id, String observacao, Date dataCriacao, Date dataVencimento, String professor,
			TipoSerie tipoSerie) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.dataCriacao = dataCriacao;
		this.dataVencimento = dataVencimento;
		this.professor = professor;
		this.tipoSerie = (tipoSerie == null) ? null : tipoSerie.getCodigo();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}


	public TipoSerie getTipoSerie() {
		return TipoSerie.toEnum(tipoSerie);
	}


	public void setTipoSerie(TipoSerie tipoSerie) {
		this.tipoSerie = tipoSerie.getCodigo();
	}
	
	public Set<ItemSerie> getItens() {
		return itens;
	}


	public void setItens(Set<ItemSerie> itens) {
		this.itens = itens;
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
		Serie other = (Serie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
