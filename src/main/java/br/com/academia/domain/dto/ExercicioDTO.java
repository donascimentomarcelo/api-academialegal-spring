package br.com.academia.domain.dto;

import java.io.Serializable;

import br.com.academia.domain.Exercicio;

public class ExercicioDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	
	public ExercicioDTO() 
	{
	
	}


	public ExercicioDTO(Exercicio exercicio) 
	{
		id = exercicio.getId();
		nome = exercicio.getNome();
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
	
	
	
	

}
