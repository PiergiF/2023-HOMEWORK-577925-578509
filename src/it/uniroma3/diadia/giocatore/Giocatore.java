package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore () {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfuAttuali) {
		cfu = cfuAttuali;		
	}
	
	static public int getCfuIniziali() {
		return CFU_INIZIALI;
	}
	
	public String getDescrizione(){
		return toString();
	}
	
	public boolean setAttrezzo(Attrezzo attrezzo) {
		if(borsa.addAttrezzo(attrezzo));
			return true;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public String toString() {
		return ("Ti rimangono " + getCfu() + "cfu.");
	}
}