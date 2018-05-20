package br.com.academia.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Avaliacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seqAvaliacao")
	@SequenceGenerator(name = "seqAvaliacao", sequenceName = "seq_id_avaliacao")
	private Integer id;
	private double peitoral;
	private double biceps;
	private double triceps;
	private double subescapular;
	private double supraIliaca;
	private double axilarMedia;
	private double coxa;
	private double panturrilhaMedial;
	private double torax;
	private double abdomen;
	private double cintura;
	private double quadril;
	private double bracoDireito;
	private double bracoEsquerdo;
	private double antebracoDireiro;
	private double antebracoEsquerdo;
	private double coxaDireita;
	private double coxaEsquerda;
	private double pernaDireita;
	private double pernaEsquerda;
	private double ombros;
	private double pescoco;
	private double punho;
	private double joelho;
	private double tornozelo;
	private double altura;
	private double peso;
	
	@OneToOne
	@JoinColumn(name="agenda_id")
	private Agenda agenda;
	
	

	public Avaliacao()
	{

	}
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public double getPeitoral() {
		return peitoral;
	}



	public void setPeitoral(double peitoral) {
		this.peitoral = peitoral;
	}



	public double getBiceps() {
		return biceps;
	}



	public void setBiceps(double biceps) {
		this.biceps = biceps;
	}



	public double getTriceps() {
		return triceps;
	}



	public void setTriceps(double triceps) {
		this.triceps = triceps;
	}



	public double getSubescapular() {
		return subescapular;
	}



	public void setSubescapular(double subescapular) {
		this.subescapular = subescapular;
	}



	public double getSupraIliaca() {
		return supraIliaca;
	}



	public void setSupraIliaca(double supraIliaca) {
		this.supraIliaca = supraIliaca;
	}



	public double getAxilarMedia() {
		return axilarMedia;
	}



	public void setAxilarMedia(double axilarMedia) {
		this.axilarMedia = axilarMedia;
	}



	public double getCoxa() {
		return coxa;
	}



	public void setCoxa(double coxa) {
		this.coxa = coxa;
	}



	public double getPanturrilhaMedial() {
		return panturrilhaMedial;
	}



	public void setPanturrilhaMedial(double panturrilhaMedial) {
		this.panturrilhaMedial = panturrilhaMedial;
	}



	public double getTorax() {
		return torax;
	}



	public void setTorax(double torax) {
		this.torax = torax;
	}



	public double getAbdomen() {
		return abdomen;
	}



	public void setAbdomen(double abdomen) {
		this.abdomen = abdomen;
	}



	public double getCintura() {
		return cintura;
	}



	public void setCintura(double cintura) {
		this.cintura = cintura;
	}



	public double getQuadril() {
		return quadril;
	}



	public void setQuadril(double quadril) {
		this.quadril = quadril;
	}



	public double getBracoDireito() {
		return bracoDireito;
	}



	public void setBracoDireito(double bracoDireito) {
		this.bracoDireito = bracoDireito;
	}



	public double getBracoEsquerdo() {
		return bracoEsquerdo;
	}



	public void setBracoEsquerdo(double bracoEsquerdo) {
		this.bracoEsquerdo = bracoEsquerdo;
	}



	public double getAntebracoDireiro() {
		return antebracoDireiro;
	}



	public void setAntebracoDireiro(double antebracoDireiro) {
		this.antebracoDireiro = antebracoDireiro;
	}



	public double getAntebracoEsquerdo() {
		return antebracoEsquerdo;
	}



	public void setAntebracoEsquerdo(double antebracoEsquerdo) {
		this.antebracoEsquerdo = antebracoEsquerdo;
	}



	public double getCoxaDireita() {
		return coxaDireita;
	}



	public void setCoxaDireita(double coxaDireita) {
		this.coxaDireita = coxaDireita;
	}



	public double getCoxaEsquerda() {
		return coxaEsquerda;
	}



	public void setCoxaEsquerda(double coxaEsquerda) {
		this.coxaEsquerda = coxaEsquerda;
	}



	public double getPernaDireita() {
		return pernaDireita;
	}



	public void setPernaDireita(double pernaDireita) {
		this.pernaDireita = pernaDireita;
	}



	public double getPernaEsquerda() {
		return pernaEsquerda;
	}



	public void setPernaEsquerda(double pernaEsquerda) {
		this.pernaEsquerda = pernaEsquerda;
	}



	public double getOmbros() {
		return ombros;
	}



	public void setOmbros(double ombros) {
		this.ombros = ombros;
	}



	public double getPescoco() {
		return pescoco;
	}



	public void setPescoco(double pescoco) {
		this.pescoco = pescoco;
	}



	public double getPunho() {
		return punho;
	}



	public void setPunho(double punho) {
		this.punho = punho;
	}



	public double getJoelho() {
		return joelho;
	}



	public void setJoelho(double joelho) {
		this.joelho = joelho;
	}



	public double getTornozelo() {
		return tornozelo;
	}



	public void setTornozelo(double tornozelo) {
		this.tornozelo = tornozelo;
	}



	public double getAltura() {
		return altura;
	}



	public void setAltura(double altura) {
		this.altura = altura;
	}



	public double getPeso() {
		return peso;
	}



	public void setPeso(double peso) {
		this.peso = peso;
	}



	public Agenda getAgenda() {
		return agenda;
	}



	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}