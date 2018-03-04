package br.com.academia;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.academia.domain.Grupo;
import br.com.academia.repositories.GrupoRepository;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner{

	@Autowired
	private GrupoRepository grupoRepository;
		
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Grupo g1 = new Grupo(null, "Peitoral");
		Grupo g2 = new Grupo(null, "Tríceps");
		Grupo g3 = new Grupo(null, "Bíceps");
		Grupo g4 = new Grupo(null, "Costas");
		Grupo g5 = new Grupo(null, "Perna");
		
		grupoRepository.save(Arrays.asList(g1, g2, g3, g4, g5));
	}
}
