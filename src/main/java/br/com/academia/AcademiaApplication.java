package br.com.academia;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.academia.domain.Exercicio;
import br.com.academia.domain.Grupo;
import br.com.academia.domain.ItemSerie;
import br.com.academia.domain.Serie;
import br.com.academia.domain.Solicitacao;
import br.com.academia.domain.Usuario;
import br.com.academia.domain.enums.Perfil;
import br.com.academia.domain.enums.StatusSerie;
import br.com.academia.domain.enums.TipoSerie;
import br.com.academia.repositories.ExercicioRepository;
import br.com.academia.repositories.GrupoRepository;
import br.com.academia.repositories.ItemSerieRepository;
import br.com.academia.repositories.SerieRepository;
import br.com.academia.repositories.SolicitacaoRepository;
import br.com.academia.repositories.UsuarioRepository;

@SpringBootApplication
public class AcademiaApplication implements CommandLineRunner{

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;  

	@Autowired
	private SerieRepository serieRepository;
	
	@Autowired
	private ItemSerieRepository itemSerieRepository; 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
		
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
		
		Usuario u1 = new Usuario(null, "marcelojunin2010@hotmail.com", "Marcelo Nascimento", bCryptPasswordEncoder.encode("123"));
		Usuario u2 = new Usuario(null, "angela@hotmail.com", "Angela Silva", bCryptPasswordEncoder.encode("123"));
		Usuario u3 = new Usuario(null, "bruna@hotmail.com", "Bruna Cordeiro", bCryptPasswordEncoder.encode("123"));
		Usuario u4 = new Usuario(null, "celia@hotmail.com", "Célia Alves", bCryptPasswordEncoder.encode("123"));
		
		u1.addPerfil(Perfil.ADMIN);
		u3.addPerfil(Perfil.PROFESSOR);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Solicitacao s1 = new Solicitacao(null, format.parse("08/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u1);
		Solicitacao s2 = new Solicitacao(null, format.parse("08/03/2018"), TipoSerie.DEFINICAO, StatusSerie.CONCLUIDO, "Loren ipsun ...", null, u2);
		Solicitacao s3 = new Solicitacao(null, format.parse("08/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u2);
		Solicitacao s4 = new Solicitacao(null, format.parse("08/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u3);
		
		u1.getSolicitacoes().addAll(Arrays.asList(s1));
		u2.getSolicitacoes().addAll(Arrays.asList(s2, s3));
		u3.getSolicitacoes().addAll(Arrays.asList(s4));
		
		
		grupoRepository.save(Arrays.asList(g1, g2, g3, g4));
		
		exercicioRepository.save(
				Arrays.asList(ex1, ex2, ex3, ex4, 
							  ex5, ex6, ex7, ex8,
							  ex9, ex10, ex11, ex12,
							  ex13, ex14, ex15, ex16));
		
		usuarioRepository.save(Arrays.asList(u1, u2, u3, u4));
		
		solicitacaoRepository.save(Arrays.asList(s1, s2, s3, s4));
		
		//RELACIONANDO SERIE COM ITEM SERIE
		
		Serie sr1 = new Serie(null, "Loren ipsun ...", format.parse("08/03/2018"), format.parse("08/05/2018"), "Manuel", TipoSerie.HIPERTROFIA, s1);
		Serie sr2 = new Serie(null, "Loren ipsun ...", format.parse("01/02/2018"), format.parse("01/04/2018"), "Joana", TipoSerie.DEFINICAO, s2);
		Serie sr3 = new Serie(null, "Loren ipsun ...", format.parse("15/01/2018"), format.parse("15/03/2018"), "Maria", TipoSerie.HIPERTROFIA, s3);
		
		serieRepository.save(Arrays.asList(sr1, sr2, sr3));
		
		ItemSerie is1 = new ItemSerie(sr1, ex1, "10 - 10 - 10", "A", null);
		ItemSerie is2 = new ItemSerie(sr1, ex2, "10 - 10 - 10", "A", null);
		ItemSerie is3 = new ItemSerie(sr1, ex3, "10 - 10 - 10", "A", null);
		
		ItemSerie is4 = new ItemSerie(sr2, ex4, "15 - 15 - 20", "A", null);
		ItemSerie is5 = new ItemSerie(sr2, ex5, "15 - 15 - 20", "A", null);
		ItemSerie is6 = new ItemSerie(sr2, ex6, "15 - 15 - 20", "A", null);
		ItemSerie is7 = new ItemSerie(sr2, ex7, "15 - 15 - 20", "B", null);
		ItemSerie is8 = new ItemSerie(sr2, ex8, "15 - 15 - 20", "B", null);
		ItemSerie is9 = new ItemSerie(sr2, ex9, "15 - 15 - 20", "B", null);
		
		ItemSerie is10 = new ItemSerie(sr3, ex13, "10 - 10 - 10", "A", null);
		ItemSerie is11 = new ItemSerie(sr3, ex14, "10 - 10 - 10", "A", null);
		ItemSerie is12 = new ItemSerie(sr3, ex15, "10 - 10 - 10", "A", null);
		
		sr1.getItens().addAll(Arrays.asList(is1, is2, is3));
		sr2.getItens().addAll(Arrays.asList(is4, is5, is6, is7, is8, is9));
		sr3.getItens().addAll(Arrays.asList(is10, is11, is12));
		
		itemSerieRepository.save(Arrays.asList(is1, is2, is3, is4, is5, is6, is7, is8, is9, is10, is11, is12));
		
	}
}
