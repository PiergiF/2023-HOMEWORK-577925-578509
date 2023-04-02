package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	/**
	 * Aggiunge l'attrezzo preso in input alla borsa
	 * @return true se è stato possiibile aggiongere l'attrezzo, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce il peso massimo che la borsa può sopportare
	 * @return il peso massimo della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Restituisce l'attrezzo cercato in input
	 * @return il riferimento all'oggetto
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	/**
	 * Restituisce il peso dell'attrezzo cercato in borsa
	 * @return il peso dell'attrezzo
	 */

	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/**
	 * Indica se la borsa è vuota
	 * @return true se la borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Indica se la borsa contiene un determinato oggetto 
	 * @return true se l'attrezzo è presente, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Rimuove l'attrezzo dalla borsa
	 * @return l'attrezzo rimosso, null se non è presente l'attrezzo
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo, Partita partita) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				a = attrezzi[i];
				partita.getStanzaCorrente().addAttrezzo(a);
				this.numeroAttrezzi--;
				attrezzi[i] = null;
				for(int j=i+1; j<this.attrezzi.length;j++) {
					if(attrezzi[j] != null) {
						attrezzi[j-1] = attrezzi [j];
						attrezzi[j] = null;
					}
				}
			}
		return a;
	}

	/**
	 * Restituisce la descrizione dello stato della borsa al momento della chiamata
	 * @return una stringa che indica lo stato della borsa
	 */
	public String getDescrizione() {
		return this.toString();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}