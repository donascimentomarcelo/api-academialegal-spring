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
		Grupo g4 = new Grupo(null, "Dorsais");
		Grupo g5 = new Grupo(null, "Lombares");
		Grupo g6 = new Grupo(null, "Ante-braço");
		Grupo g7 = new Grupo(null, "Ombro");
		Grupo g9 = new Grupo(null, "Trapézio");
		Grupo g10 = new Grupo(null, "Quadríceps");
		Grupo g11 = new Grupo(null, "Posteriores coxas");
		Grupo g12 = new Grupo(null, "Adutores");
		Grupo g13 = new Grupo(null, "Glúteos");
		Grupo g14 = new Grupo(null, "Panturrilha");
		Grupo g15 = new Grupo(null, "Abdomen Infra");
		Grupo g16 = new Grupo(null, "Abdomen Supra");
		Grupo g17 = new Grupo(null, "Abdomen Oblico");
		Grupo g18 = new Grupo(null, "Outros");
		
		
		Exercicio ex1 = new Exercicio(null, "Supino reto", g1);
		Exercicio ex2 = new Exercicio(null, "Supino 45º", g1);
		Exercicio ex3 = new Exercicio(null, "Supino canadense", g1);
		Exercicio ex4 = new Exercicio(null, "Flexão", g1);
		
		Exercicio ex5 = new Exercicio(null, "Triceps invertido", g2);
		Exercicio ex6 = new Exercicio(null, "Triceps coice", g2);
		Exercicio ex7 = new Exercicio(null, "Triceps canadense", g2);
		Exercicio ex8 = new Exercicio(null, "Triceps testa", g2);
		
		Exercicio ex9 = new Exercicio(null, "Bíceps rosca", g3);
		Exercicio ex10 = new Exercicio(null, "Bíceps canadense", g3);
		Exercicio ex11 = new Exercicio(null, "Bíceps unilateral", g3);
		Exercicio ex12 = new Exercicio(null, "Bíceps banco", g3);
		
		Exercicio ex13 = new Exercicio(null, "Pulley frontal", g4);
		Exercicio ex14 = new Exercicio(null, "Pulley costas", g4);
		Exercicio ex15 = new Exercicio(null, "Puxada fechada", g4);
		Exercicio ex16 = new Exercicio(null, "Puxada aberta", g4);
		
		Exercicio ex17 = new Exercicio(null, "Alongamento de coluna", g5);
		Exercicio ex18 = new Exercicio(null, "Rolamento de joelhos", g5);
		Exercicio ex19 = new Exercicio(null, "Extensão", g5);
		Exercicio ex20 = new Exercicio(null, "Fortalecimento abdominal profundo", g5);
		
		Exercicio ex21 = new Exercicio(null, "Bíceps invertido", g6);
		Exercicio ex22 = new Exercicio(null, "Bíceps martelo", g6);
		Exercicio ex23 = new Exercicio(null, "Rosca punho", g6);
		Exercicio ex24 = new Exercicio(null, "Rosca punho invertido", g6);
		
		Exercicio ex25 = new Exercicio(null, "Elevação lateral", g7);
		Exercicio ex26 = new Exercicio(null, "Elevação frontal", g7);
		Exercicio ex27 = new Exercicio(null, "Desenvolvimento com barra", g7);
		Exercicio ex28 = new Exercicio(null, "Desenvolvimento com halteres", g7);
		
		Exercicio ex29 = new Exercicio(null, "Encolhimento com halteres", g9);
		Exercicio ex30 = new Exercicio(null, "Encolhimento com barra pela frente", g9);
		Exercicio ex31 = new Exercicio(null, "Encolhimento com barra por trás", g9);
		Exercicio ex32 = new Exercicio(null, "Remada alta com barra", g9);
		
		Exercicio ex33 = new Exercicio(null, "Agachamento", g10);
		Exercicio ex34 = new Exercicio(null, "Leg Press", g10);
		Exercicio ex35 = new Exercicio(null, "Agachamento frontal", g10);
		Exercicio ex36 = new Exercicio(null, "Cadeira extensora", g10);
		
		Exercicio ex37 = new Exercicio(null, "Rosca de perna deitado", g11);
		Exercicio ex38 = new Exercicio(null, "Levantamento terra romeno", g11);
		Exercicio ex39 = new Exercicio(null, "Levantamento terra sumô", g11);
		Exercicio ex40 = new Exercicio(null, "Giro com kettlebell", g11);
		
		Exercicio ex41 = new Exercicio(null, "Agachamento de adutor com haltere", g12);
		Exercicio ex42 = new Exercicio(null, "Adutores em polia baixa", g12);
		Exercicio ex43 = new Exercicio(null, "Agachamento plié", g12);
		Exercicio ex44 = new Exercicio(null, "Máquina adutora", g12);
		
		Exercicio ex45 = new Exercicio(null, "Ponte com uma perna só", g13);
		Exercicio ex46 = new Exercicio(null, "Avanço com halteres", g13);
		Exercicio ex47 = new Exercicio(null, "Coice para glúteos", g13);
		Exercicio ex48 = new Exercicio(null, "Coice no cabo", g13);
		
		Exercicio ex49 = new Exercicio(null, "Panturrilha em pé", g14);
		Exercicio ex50 = new Exercicio(null, "Panturrilha no leg press", g14);
		Exercicio ex51 = new Exercicio(null, "Elevação de panturrilha em pé", g14);
		Exercicio ex52 = new Exercicio(null, "Elevação de panturrilha com uma perna só", g14);
		
		Exercicio ex53 = new Exercicio(null, "Abdominais", g15);
		Exercicio ex54 = new Exercicio(null, "Tesoura", g15);
		Exercicio ex55 = new Exercicio(null, "Abdominais invertido no banco declinado", g15);
		Exercicio ex56 = new Exercicio(null, "Elevação das pernas na barra", g15);
		
		Exercicio ex57 = new Exercicio(null, "Abdominal Supra na prancha", g16);
		Exercicio ex58 = new Exercicio(null, "Abdominal supra no chão", g16);
		Exercicio ex59 = new Exercicio(null, "Abdominal na bola Suíça", g16);
		Exercicio ex60 = new Exercicio(null, "Abdominal no pulley", g16);
		
		Exercicio ex61 = new Exercicio(null, "Prancha Lateral", g17);
		Exercicio ex62 = new Exercicio(null, "Inclinação lateral com Halteres", g17);
		Exercicio ex63 = new Exercicio(null, "Abdominal Bicicleta", g17);
		Exercicio ex64 = new Exercicio(null, "Abdominal pendurado lateral", g17);
		
		Exercicio ex65 = new Exercicio(null, "Aeróbico", g18);
		
		g1.getExercicios().addAll(Arrays.asList(ex1, ex2, ex3, ex4));
		g2.getExercicios().addAll(Arrays.asList(ex5, ex6, ex7, ex8));
		g3.getExercicios().addAll(Arrays.asList(ex9, ex10, ex11, ex12));
		g4.getExercicios().addAll(Arrays.asList(ex13, ex14, ex15, ex16));
		g5.getExercicios().addAll(Arrays.asList(ex17, ex18, ex19, ex20));
		g6.getExercicios().addAll(Arrays.asList(ex21, ex22, ex23, ex24));
		g7.getExercicios().addAll(Arrays.asList(ex25, ex26, ex27, ex28));
		g9.getExercicios().addAll(Arrays.asList(ex29, ex30, ex31, ex32));
		g10.getExercicios().addAll(Arrays.asList(ex33, ex34, ex35, ex36));
		g11.getExercicios().addAll(Arrays.asList(ex37, ex38, ex39, ex40));
		g12.getExercicios().addAll(Arrays.asList(ex41, ex42, ex43, ex44));
		g13.getExercicios().addAll(Arrays.asList(ex45, ex46, ex47, ex48));
		g14.getExercicios().addAll(Arrays.asList(ex49, ex50, ex51, ex52));
		g15.getExercicios().addAll(Arrays.asList(ex53, ex54, ex55, ex56));
		g16.getExercicios().addAll(Arrays.asList(ex57, ex58, ex59, ex60));
		g17.getExercicios().addAll(Arrays.asList(ex61,ex62,  ex63, ex64));
		g18.getExercicios().addAll(Arrays.asList(ex65));
		
		Usuario u1 = new Usuario(null, "marcelojunin2010@hotmail.com", "Marcelo Nascimento", bCryptPasswordEncoder.encode("123"));
		Usuario u2 = new Usuario(null, "angela@hotmail.com", "Angela Silva", bCryptPasswordEncoder.encode("123"));
		Usuario u3 = new Usuario(null, "bruna@hotmail.com", "Bruna Cordeiro", bCryptPasswordEncoder.encode("123"));
		Usuario u4 = new Usuario(null, "celia@hotmail.com", "Célia Alves", bCryptPasswordEncoder.encode("123"));
		
		u1.addPerfil(Perfil.ADMIN);
		u1.addPerfil(Perfil.PROFESSOR);
		u1.removePerfil(Perfil.ALUNO);
		u3.addPerfil(Perfil.PROFESSOR);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		Solicitacao s1 = new Solicitacao(null, format.parse("01/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.CONCLUIDO,
									"Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica", 
									"Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica", u1, u1.getNome());
		Solicitacao s2 = new Solicitacao(null, format.parse("15/02/2018"), TipoSerie.DEFINICAO, StatusSerie.CONCLUIDO, "Loren ipsun ...", null, u2, u2.getNome());
		Solicitacao s3 = new Solicitacao(null, format.parse("10/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u2, u2.getNome());
		Solicitacao s4 = new Solicitacao(null, format.parse("26/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u3, u3.getNome());
		Solicitacao s5 = new Solicitacao(null, format.parse("29/03/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u3, u3.getNome());
		Solicitacao s6 = new Solicitacao(null, format.parse("01/04/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u3, u3.getNome());
		Solicitacao s7 = new Solicitacao(null, format.parse("03/04/2018"), TipoSerie.HIPERTROFIA, StatusSerie.PENDENTE, "Loren ipsun ...", null, u3, u3.getNome());
		Solicitacao s8 = new Solicitacao(null, format.parse("03/04/2018"), TipoSerie.HIPERTROFIA, StatusSerie.REJEITADO, "Loren ipsun ...", null, u3, u3.getNome());
		
		u1.getSolicitacoes().addAll(Arrays.asList(s1));
		u2.getSolicitacoes().addAll(Arrays.asList(s2, s3));
		u3.getSolicitacoes().addAll(Arrays.asList(s4, s5, s6, s7, s8));
		
		
		grupoRepository.save(
				Arrays.asList(g1, g2, g3, g4, 
							  g5, g6, g7, g9, 
							  g10, g11, g12, g13,
							  g14, g15, g16, g17, 
							  g18));
		
		exercicioRepository.save(
				Arrays.asList(ex1, ex2, ex3, ex4, 
							  ex5, ex6, ex7, ex8,
							  ex9, ex10, ex11, ex12,
							  ex13, ex14, ex15, ex16,
							  ex17, ex18, ex19, ex20,
							  ex21, ex22, ex23, ex24,
							  ex25, ex26, ex27, ex28,
							  ex29, ex30, ex31, ex32,
							  ex33, ex34, ex35, ex36,
							  ex37, ex38, ex39, ex40,
							  ex41, ex42, ex43, ex44,
							  ex45, ex46, ex47, ex48,
							  ex49, ex50, ex51, ex52,
							  ex53, ex54, ex55, ex56,
							  ex57, ex58, ex59, ex60,
							  ex61,ex62,  ex63, ex64,
							  ex65));
		
		usuarioRepository.save(Arrays.asList(u1, u2, u3, u4));
		
		solicitacaoRepository.save(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8));
		
		//RELACIONANDO SERIE COM ITEM SERIE
		
		Serie sr1 = new Serie(null, "Loren ipsun ...", format.parse("08/03/2018"), format.parse("08/05/2018"), "Manuel", TipoSerie.HIPERTROFIA, s1);
		Serie sr2 = new Serie(null, "Loren ipsun ...", format.parse("01/02/2018"), format.parse("01/04/2018"), "Joana", TipoSerie.DEFINICAO, s2);
		Serie sr3 = new Serie(null, "Loren ipsun ...", format.parse("15/01/2018"), format.parse("15/03/2018"), "Maria", TipoSerie.HIPERTROFIA, s3);
		
		serieRepository.save(Arrays.asList(sr1, sr2, sr3));
		
		ItemSerie is1 = new ItemSerie(sr1, ex1, "10 - 10 - 10", "A", null, 0);
		ItemSerie is2 = new ItemSerie(sr1, ex2, "10 - 10 - 10", "A", null, 1);
		ItemSerie is3 = new ItemSerie(sr1, ex3, "10 - 10 - 10", "A", null, 2);
		
		ItemSerie is4 = new ItemSerie(sr2, ex4, "15 - 15 - 20", "A", null, 0);
		ItemSerie is5 = new ItemSerie(sr2, ex5, "15 - 15 - 20", "A", null, 1);
		ItemSerie is6 = new ItemSerie(sr2, ex6, "15 - 15 - 20", "A", null, 2);
		ItemSerie is7 = new ItemSerie(sr2, ex7, "15 - 15 - 20", "B", null, 3);
		ItemSerie is8 = new ItemSerie(sr2, ex8, "15 - 15 - 20", "B", null, 4);
		ItemSerie is9 = new ItemSerie(sr2, ex9, "15 - 15 - 20", "B", null, 5);
		
		ItemSerie is10 = new ItemSerie(sr3, ex13, "10 - 10 - 10", "A", null, 0);
		ItemSerie is11 = new ItemSerie(sr3, ex14, "10 - 10 - 10", "A", null, 1);
		ItemSerie is12 = new ItemSerie(sr3, ex15, "10 - 10 - 10", "A", null, 2);
		
		sr1.getItens().addAll(Arrays.asList(is1, is2, is3));
		sr2.getItens().addAll(Arrays.asList(is4, is5, is6, is7, is8, is9));
		sr3.getItens().addAll(Arrays.asList(is10, is11, is12));
		
		itemSerieRepository.save(Arrays.asList(is1, is2, is3, is4, is5, is6, is7, is8, is9, is10, is11, is12));
		
	}
}
