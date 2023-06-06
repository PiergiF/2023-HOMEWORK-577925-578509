package it.uniroma3.diadia.ambienti;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	//static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	static final private int NUMERO_MASSIMO_DIREZIONI = Configuratore.getNumeroMassimoDirezioni();
	static final private int NUMERO_MASSIMO_ATTREZZI = Configuratore.getNumeroMassimoAttrezzi();
	
	private String nome;
	private List <Attrezzo> attrezzi;
	//private int numeroAttrezzi;
	private Map <String,Stanza> stanzeAdiacenti;
	//private int numeroStanzeAdiacenti;
	private List <String> direzioni;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<String, Stanza>();
		this.direzioni = new ArrayList<String>(NUMERO_MASSIMO_DIREZIONI);
		this.attrezzi = new ArrayList<Attrezzo>(NUMERO_MASSIMO_ATTREZZI);
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		if(!this.stanzeAdiacenti.containsKey(direzione) && (this.direzioni.size() < NUMERO_MASSIMO_DIREZIONI)){
			this.stanzeAdiacenti.put(direzione, stanza);
			this.direzioni.add(direzione);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce il nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Restituisce il numero di attrezzi attualmente presenti nella stanza.
	 * @return il numero di attrezzi presenti nella stanza
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Restituisce la capienza massima della stanza.
	 * @return il numero massimo di attrezzi inseribili nella stanza
	 */
	static public int getNumeroMassimoAttrezzi() {
		return NUMERO_MASSIMO_ATTREZZI;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false altrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getNumeroAttrezzi() < NUMERO_MASSIMO_ATTREZZI && (!this.attrezzi.contains(attrezzo))) {
			return this.attrezzi.add(attrezzo);
		}
		else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\nTi trovi in: " + this.nome);
		risultato.append("\n Uscite:");
		for (String direzione : this.direzioni)
			if (direzione!=null)
				if(this.direzioni.indexOf(direzione) == this.direzioni.size()-1)
					risultato.append(" " + direzione + ";");
				else
					risultato.append(" " + direzione + ",");
		risultato.append("\n Attrezzi nella stanza:");
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo!=null)
				if(this.attrezzi.indexOf(attrezzo) == this.attrezzi.size()-1)
					risultato.append(" " + attrezzo + ";");
				else
					risultato.append(" " + attrezzo + ",");
		}
		if(this.haPersonaggio()) {
			if(this.getPersonaggio().haSalutato())
				risultato.append("\nC'è un mago qui");
			else
				risultato.append("\nC'è un personaggio nella stanza!");
		}

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				trovato = true;
		}
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo!=null) {		//prendi solo i campi non null di attrezzo
				if (attrezzo.getNome().equals(nomeAttrezzo))
					attrezzoCercato = attrezzo;
			}
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for (Attrezzo tmp : this.attrezzi) {
			if (tmp.equals(attrezzo)) {
				return this.attrezzi.remove(attrezzo);
			}
		}
		return false;
	}

	public String getNomeStanza() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("\nTi trovi in: " + this.nome);
		return risultato.toString();
	}

	public List<String> getDirezioni() {
		return this.direzioni;
	}
	
	public Map<String,Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	public void setPersonaggio(AbstractPersonaggio personaggio) {
        this.personaggio = personaggio;
    }
	
	public AbstractPersonaggio getPersonaggio() {
        return this.personaggio;
	}
	
	public boolean haPersonaggio() {
		if(this.personaggio != null)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza s = (Stanza)o;
		return this.getNome().equals(s.getNome());
	}
	

}