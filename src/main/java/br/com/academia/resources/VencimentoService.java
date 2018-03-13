package br.com.academia.resources;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.academia.domain.Serie;

@Service
public class VencimentoService {

	
	public void preencherDataVencimento(Serie serie)
	{
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, 60);
		serie.setDataVencimento(cal.getTime());
	}
}
