package br.com.academia.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ExercicioNewDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	private Integer grupoId;

	public ExercicioNewDTO() 
	{
	
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


	public Integer getGrupoId() {
		return grupoId;
	}


	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	
	
	
	

}
