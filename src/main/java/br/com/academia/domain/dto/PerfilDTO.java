package br.com.academia.domain.dto;

import java.io.Serializable;

import br.com.academia.services.validation.UsuarioInsert;

@UsuarioInsert
public class PerfilDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer perfil;
	
	
	public PerfilDTO() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
	
}
