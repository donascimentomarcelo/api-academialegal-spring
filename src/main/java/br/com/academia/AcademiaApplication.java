package br.com.academia;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.Grupo;
import br.com.academia.repositories.ExercicioRepository;
import br.com.academia.repositories.GrupoRepository;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner{

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private ExercicioRepository exercicioRepository;
		
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Grupo g1 = new Grupo(null, "Peitoral");
		Grupo g2 = new Grupo(null, "Tríceps");
		Grupo g3 = new Grupo(null, "Bíceps");
		Grupo g4 = new Grupo(null, "Costas");
		
		Exercicio ex1 = new Exercicio(null, "Supino reto", g1);
		Exercicio ex2 = new Exercicio(null, "Supino 45º", g1);
		Exercicio ex3 = new Exercicio(null, "Supino canadense", g1);
		Exercicio ex4 = new Exercicio(null, "Flexão", g1);
		
		Exercicio ex5 = new Exercicio(null, "Triceps invertido", g2);
		Exercicio ex6 = new Exercicio(null, "Triceps coice", g2);
		Exercicio ex7 = new Exercicio(null, "Triceps canadense", g2);
		Exercicio ex8 = new Exercicio(null, "Triceps testa", g2);
		
		Exercicio ex9 = new Exercicio(null, "Bíceps rosca", g3);
		Exercicio ex10 = new Exercicio(null, "Bíceps invertido", g3);
		Exercicio ex11 = new Exercicio(null, "Bíceps unilateral", g3);
		Exercicio ex12 = new Exercicio(null, "Bíceps banco", g3);
		
		Exercicio ex13 = new Exercicio(null, "Pulley frontal", g4);
		Exercicio ex14 = new Exercicio(null, "Pulley costas", g4);
		Exercicio ex15 = new Exercicio(null, "Puxada fechada", g4);
		Exercicio ex16 = new Exercicio(null, "Puxada aberta", g4);
		
		g1.getExercicios().addAll(Arrays.asList(ex1, ex2, ex3, ex4));
		g2.getExercicios().addAll(Arrays.asList(ex5, ex6, ex7, ex8));
		g3.getExercicios().addAll(Arrays.asList(ex9, ex10, ex11, ex12));
		g4.getExercicios().addAll(Arrays.asList(ex13, ex14, ex15, ex16));
		
		grupoRepository.save(Arrays.asList(g1, g2, g3, g4));
		
		exercicioRepository.save(
				Arrays.asList(ex1, ex2, ex3, ex4, 
							  ex5, ex6, ex7, ex8,
							  ex9, ex10, ex11, ex12,
							  ex13, ex14, ex15, ex16));
	}
}
