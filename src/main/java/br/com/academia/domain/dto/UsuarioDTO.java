package br.com.academia.domain.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.academia.domain.Usuario;
import br.com.academia.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioDTO {
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="E-mail inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=6, max=12, message="O tamanho deve ser entre 6 e 12 caracteres")
	private String senha;
	//private Perfis perfis;
	
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		senha = usuario.getSenha();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
