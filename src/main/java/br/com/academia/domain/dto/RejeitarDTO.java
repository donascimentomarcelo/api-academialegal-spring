package br.com.academia.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.academia.domain.Solicitacao;

public class RejeitarDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=10, max=80, message="O tamanho deve ser entre 10 e 80 caracteres")
	private String justificativa;
	
	
	
	public RejeitarDTO() {
		super();
	}
		
	public RejeitarDTO(Solicitacao solicitacao) {
		super();
		id = solicitacao.getId();
		justificativa = solicitacao.getJustificativa();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	
}
