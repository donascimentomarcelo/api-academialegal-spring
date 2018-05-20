package br.com.academia.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.academia.domain.Agenda;

public class AgendaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern="HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date startTime;
	@JsonFormat(pattern="HH:mm:ss", locale = "pt-BR", timezone = "Brazil/East")
	private Date endTime;
	private String aluno;
	private String professor;
	
	public AgendaDTO(Agenda x) 
	{
		this.id = x.getId();
		this.startTime = x.getStartTime();
		this.endTime = x.getEndTime();
		this.aluno = x.getAluno();
		this.professor = x.getProfessor();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}
	

}
