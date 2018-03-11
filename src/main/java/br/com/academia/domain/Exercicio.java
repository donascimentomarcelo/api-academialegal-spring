package br.com.academia.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exercicio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqExercicio")
	@SequenceGenerator(name = "seqExercicio", sequenceName = "seq_id_grupo")
	private Integer id;
	private String nome;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.exercicio")
	private Set<ItemSerie> itens = new HashSet<>();
	
	public Exercicio()
	{
		
	}

	public Exercicio(Integer id, String nome, Grupo grupo) {
		super();
		this.id = id;
		this.nome = nome;
		this.grupo = grupo;
	}
	
	@JsonIgnore
	public List<Serie> getSeries()
	{
		List<Serie> lista = new ArrayList<>();
		for(ItemSerie x: itens)
		{
			lista.add(x.getSerie());
		}
		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
		Exercicio other = (Exercicio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
