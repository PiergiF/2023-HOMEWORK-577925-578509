package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.Map;
//import java.util.Scanner;
import java.util.HashMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
//import it.uniroma3.diadia.comandi.Comando;
//import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import it.uniroma3.diadia.personaggi.*;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	/*public Labirinto (String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto (nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaVincente = c.getStanzaVincente();
	}*/

	public Labirinto (String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		LabirintoBuilder labirintoBuilder = new LabirintoBuilder(this);
		CaricatoreLabirinto c = new CaricatoreLabirinto (nomeFile,labirintoBuilder);
		c.carica();
		//this.stanzaIniziale = c.getStanzaIniziale();
		//this.stanzaVincente = c.getStanzaVincente();
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}
	
	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
	
	/*public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException{
		return new LabirintoBuilder(labirinto);
	}*/
	
	public static class LabirintoBuilder {
		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> mappaLabirinto;
		//private Map <String, String> direzioniOpposte;
		
		public LabirintoBuilder(Labirinto labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.mappaLabirinto = new HashMap<>();
			this.labirinto = labirinto;
			//direzioniOpposte = new HashMap<String, String>();
			//this.setDirezioniOpposte();
		}
		
		/*public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.mappaLabirinto = new HashMap<>();
			direzioniOpposte = new HashMap<String, String>();
			this.setDirezioniOpposte();
		}*/
		
//		private void setDirezioniOpposte() {
//			this.direzioniOpposte.put("nord", "sud");
//			this.direzioniOpposte.put("sud","nord");
//			this.direzioniOpposte.put("ovest","est" );
//			this.direzioniOpposte.put("est", "ovest");
//		}
		
		/*public LabirintoBuilder addStanzaIniziale(String stanza) {
			this.mappaLabirinto.put(stanza, new Stanza(stanza));
			//this.labirinto.setStanzaIniziale(this.mappaLabirinto.get(stanza));
			//this.stanzaCorrente = this.labirinto.getStanzaIniziale();
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			System.out.print("PROVA: Stanza iniziale " + stanza + "\nStanza iniziale in mappa: " + this.mappaLabirinto.get(stanza));
			return this;
		}*/
		
		/*public LabirintoBuilder addStanzaVincente(String stanza) {
			this.mappaLabirinto.put(stanza, new Stanza(stanza));
			//this.labirinto.setStanzaVincente(this.mappaLabirinto.get(stanza));
			//this.stanzaCorrente = this.labirinto.getStanzaVincente();
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			System.out.print("PROVA: Stanza vincente " + stanza + "\nStanza vincente in mappa: " + this.mappaLabirinto.get(stanza));
			return this;
		}*/
		
		public LabirintoBuilder addStanza(String stanza) {
			this.mappaLabirinto.put(stanza, new Stanza(stanza));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
			this.mappaLabirinto.put(stanzaIniziale, new Stanza(stanzaIniziale));
			this.setStanzaIniziale(stanzaIniziale);
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanzaIniziale);
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
			this.mappaLabirinto.put(stanzaVincente, new Stanza(stanzaVincente));
			this.setStanzaVincente(stanzaVincente);
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanzaVincente);
			return this;
		}
		
		public LabirintoBuilder setStanzaIniziale(String nomeStanzaIniziale) {
			if(this.mappaLabirinto.get(nomeStanzaIniziale) == null)
				this.mappaLabirinto.put(nomeStanzaIniziale, new Stanza(nomeStanzaIniziale));
			else
				this.labirinto.setStanzaIniziale(this.mappaLabirinto.get(nomeStanzaIniziale));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(nomeStanzaIniziale);
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(String nomeStanzaVincente) {
			if(this.mappaLabirinto.get(nomeStanzaVincente) == null)
				this.mappaLabirinto.put(nomeStanzaVincente, new Stanza(nomeStanzaVincente));
			else
				this.labirinto.setStanzaVincente(this.mappaLabirinto.get(nomeStanzaVincente));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(nomeStanzaVincente);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String stanza, String DirezioneBloccata,String OgettoSbloccante) {
			this.mappaLabirinto.put(stanza, new StanzaBloccata(stanza, DirezioneBloccata, OgettoSbloccante));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String stanza,String oggettoLuminoso) {
			this.mappaLabirinto.put(stanza, new StanzaBuia(stanza, oggettoLuminoso));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String stanza, int soglia) {
			this.mappaLabirinto.put(stanza, new StanzaMagica(stanza,soglia));
			this.ultimaStanzaAggiunta = this.mappaLabirinto.get(stanza);
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
			if(this.mappaLabirinto.containsKey(stanzaCorrente)) {
				Stanza c = this.mappaLabirinto.get(stanzaCorrente);
				Stanza a = this.mappaLabirinto.get(stanzaAdiacente);
				try {
					c.impostaStanzaAdiacente(direzione, a);
					//a.impostaStanzaAdiacente(this.direzioniOpposte.get(direzione), c);
					a.impostaStanzaAdiacente(Direzione.valueOf(direzione).opposta().toString(), c);
				} catch(Exception e) {
					return this;
				}
			}
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			this.mappaLabirinto.get(nomeStanza).addAttrezzo(attrezzo);
			//this.stanzaCorrente.addAttrezzo(attrezzo);
			return this;
		}
		
		//se non inserisco la stanza in cui aggiungere l'attrezzo, prendo l'ultima stanza aggiunta
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			this.mappaLabirinto.get(this.ultimaStanzaAggiunta.getNome()).addAttrezzo(attrezzo);
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String nomeAttrezzoDelPersonaggio, int pesoAttrezzoDelMago, String nomeStanzaPersonaggio, String presentazione) {
			Attrezzo attrezzoPersonaggio = new Attrezzo(nomeAttrezzoDelPersonaggio, pesoAttrezzoDelMago);
			Mago mago = new Mago(nome, presentazione, attrezzoPersonaggio);
			this.mappaLabirinto.get(nomeStanzaPersonaggio).setPersonaggio(mago);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String nomeStanzaPersonaggio, String presentazione) {
			Strega strega = new Strega(nome, presentazione);
			this.mappaLabirinto.get(nomeStanzaPersonaggio).setPersonaggio(strega);
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String nomeAttrezzoDelPersonaggio, int pesoAttrezzoDelPersonaggio, String nomeStanzaPersonaggio, String presentazione) {
			Attrezzo attrezzoPersonaggio = new Attrezzo(nomeAttrezzoDelPersonaggio, pesoAttrezzoDelPersonaggio);
			Cane cane = new Cane(nome, presentazione, attrezzoPersonaggio);
			this.mappaLabirinto.get(nomeStanzaPersonaggio).setPersonaggio(cane);
			return this;
		}
		
		/*public AbstractPersonaggio costruisciPersonaggio(String nomePersonaggio, String nomeAttrezzoDelPersonaggio, String presentazione) {
			
		    AbstractPersonaggio personaggio = null;
		    
		    try {
			    String nomeClasse = "it.uniroma3.diadia.personaggi."; //compone la stringa con it.uniroma3.diadia.comandi.Comando
			    nomeClasse += Character.toUpperCase(nomePersonaggio.charAt(0)); //prende la prima lettera del comando inserita e la fa maiuscola e lo inserisce di seguito
			    nomeClasse += nomePersonaggio.substring(1); //prende il resto del comando e lo inserisce di seguito
				
			    personaggio=(AbstractPersonaggio)Class.forName(nomeClasse).newInstance();//.getConstructor().newInstance(); //getConstructor e' giusto??
		    }catch(Exception e) {
		    
		    }
		    
		    return personaggio;
		}*/
		
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		
		public Map<String,Stanza> getMappaStanze(){
			return this.mappaLabirinto;
		}
		
	}
	
}