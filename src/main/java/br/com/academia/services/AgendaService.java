package br.com.academia.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.academia.domain.Agenda;
import br.com.academia.exceptions.DataIntegrityException;
import br.com.academia.repositories.AgendaRepository;


@Service
public class AgendaService {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	public Page<Agenda> list(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return agendaRepository.findAll(pageRequest);
	}
	
	public List<Agenda> listByDate(Date startTime)
	{
		List<Agenda> agenda = agendaRepository.findByStartTime(startTime);
		return agenda;
	}
	
	public Agenda create(Agenda agenda)
	{
		if(agenda.getStartTime().before(new Date()))
		{
			throw new DataIntegrityException("A data não pode ser menor que a atual!");
		}
		agenda.setId(null);
		addThirtyMinutes(agenda);
		checkSlotBetweenDate(agenda);
		agenda.setAluno("Crane");
		agenda.setProfessor("Brecken");
		return agendaRepository.save(agenda);
	}
	
	public void addThirtyMinutes(Agenda agenda)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(agenda.getStartTime());
		cal.add(Calendar.MINUTE, 30);
		agenda.setEndTime(cal.getTime());
	}
	
	public void checkSlotBetweenDate(Agenda agenda)
	{
		List<Agenda> cal = agendaRepository.findAll();
		
		for(Agenda key: cal)
		{
			Date start = key.getStartTime();
			Date end = key.getEndTime();
			Date selectedDateStart = agenda.getStartTime();
			Date selectedDateEnd = agenda.getEndTime();

			if(!selectedDateStart.before(convertTime(start)) && selectedDateStart.before(convertTime(end)) || !selectedDateEnd.before(convertTime(start)) && selectedDateEnd.before(convertTime(end)))
			{
				throw new DataIntegrityException("O período selecionado já está ocupado!");
			}
		}
	}
	
	public Date convertTime(Date date) 
	{    
		java.util.Calendar cal = java.util.Calendar.getInstance();  
	    cal.setTime(date);
	    return cal.getTime(); 
	}
	
}
