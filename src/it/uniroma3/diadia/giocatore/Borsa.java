package it.uniroma3.diadia.giocatore;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;


import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.Partita;
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List <Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		attrezzi = new ArrayList<Attrezzo>();
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
		attrezzi.add(attrezzo);
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
		Iterator <Attrezzo> it = this.attrezzi.iterator();
		while(it.hasNext()){
			a = it.next();
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}

	/**
	 * Restituisce il peso dell'attrezzo cercato in borsa
	 * @return il peso dell'attrezzo
	 */

	public int getPeso() {
		int peso = 0;
		Iterator <Attrezzo> it = this.attrezzi.iterator();
		while(it.hasNext()) {
			peso += it.next().getPeso();
		}
		return peso;
	}
	
	
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		Collections.sort(this.attrezzi, new ComparatoreAttrezziPerPesoENome());
		return this.attrezzi;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet <Attrezzo> insiemeAttrezzi = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		for(Attrezzo tmp : this.attrezzi) {
			insiemeAttrezzi.add(tmp);
		}
		return insiemeAttrezzi;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> mappaAttrezzi = new HashMap<Integer,Set<Attrezzo>>();
		for(Attrezzo tmp : this.attrezzi) {
			if(mappaAttrezzi.containsKey(tmp.getPeso())) {
				mappaAttrezzi.get(tmp.getPeso()).add(tmp);
			}
			else {
				mappaAttrezzi.put(tmp.getPeso(), new HashSet<Attrezzo>());
				mappaAttrezzi.get(tmp.getPeso()).add(tmp);
			}
		}
		return mappaAttrezzi;
	}

	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet <Attrezzo> insiemeAttrezzi = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerPesoENome());
		for(Attrezzo tmp : this.attrezzi) {
			insiemeAttrezzi.add(tmp);
		}
		return insiemeAttrezzi;
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
		Iterator <Attrezzo> it = this.attrezzi.iterator();
		while(it.hasNext()) {
			a = it.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				partita.getStanzaCorrente().addAttrezzo(a);
				this.numeroAttrezzi--;
				it.remove();
				return a;
			}
		}
		return null;
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
		Iterator <Attrezzo> it = this.getContenutoOrdinatoPerPeso().iterator();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while(it.hasNext()) {
				s.append(it.next().toString()+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}