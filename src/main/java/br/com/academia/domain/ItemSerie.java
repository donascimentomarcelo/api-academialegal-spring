package br.com.academia.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemSerie  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemSeriePK id = new ItemSeriePK();
	
	private String repeticoes;
	private String letra;
	private String observacao;
	private Integer ordenation; 
	

	public ItemSerie() {
		super();
	}

	public ItemSerie(Serie serie, Exercicio exercicio, String repeticoes, String letra, String observacao, Integer ordenation) {
		super();
		id.setExercicio(exercicio);
		id.setSerie(serie);
		this.repeticoes = repeticoes;
		this.letra = letra;
		this.observacao = observacao;
		this.ordenation = ordenation;
	}
	
	@JsonIgnore
	public Serie getSerie()
	{
		return id.getSerie();
	}
	
	public void setSerie(Serie serie)
	{
		id.setSerie(serie);
	}
	
	public Exercicio getExercicio()
	{
		return id.getExercicio();
	}
	
	public void setExercicio(Exercicio exercicio)
	{
		id.setExercicio(exercicio);
	}

	public ItemSeriePK getId() {
		return id;
	}

	public void setId(ItemSeriePK id) {
		this.id = id;
	}

	public String getRepeticoes() {
		return repeticoes;
	}

	public void setRepeticoes(String repeticoes) {
		this.repeticoes = repeticoes;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Integer getOrdenation() {
		return ordenation;
	}

	public void setOrdenation(Integer ordenation) {
		this.ordenation = ordenation;
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
		ItemSerie other = (ItemSerie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
