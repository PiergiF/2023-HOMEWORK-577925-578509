package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.HashMap;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private Map <String, Stanza> mappa;
	private Map <String,String> direzioniOpposte;
	
	public LabirintoBuilder() {
		labirinto = new Labirinto();
		mappa = new HashMap<String, Stanza>();
		direzioniOpposte = new HashMap<String, String>();
		this.setDirezioniOpposte();
	}
	
	private void setDirezioniOpposte() {
		this.direzioniOpposte.put("nord", "sud");
		this.direzioniOpposte.put("sud","nord");
		this.direzioniOpposte.put("ovest","est" );
		this.direzioniOpposte.put("est", "ovest");
	}
	
	public LabirintoBuilder addStanzaIniziale(String stanza) {
		this.mappa.put(stanza, new Stanza(stanza));
		this.labirinto.setStanzaIniziale(this.mappa.get(stanza));
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String stanza) {
		this.mappa.put(stanza, new Stanza(stanza));
		this.labirinto.setStanzaVincente(this.mappa.get(stanza));
		this.stanzaCorrente = this.labirinto.getStanzaVincente();
		return this;
	}
	
	public LabirintoBuilder addStanza(String stanza) {
		this.mappa.put(stanza, new Stanza(stanza));
		this.stanzaCorrente = this.mappa.get(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String stanza, String DirezioneBloccata,String OgettoSbloccante) {
		this.mappa.put(stanza, new StanzaBloccata(stanza, DirezioneBloccata, OgettoSbloccante));
		this.stanzaCorrente = this.mappa.get(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String stanza,String oggettoLuminoso) {
		this.mappa.put(stanza, new StanzaBuia(stanza, oggettoLuminoso));
		this.stanzaCorrente = this.mappa.get(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String stanza, int soglia) {
		this.mappa.put(stanza, new StanzaMagica(stanza,soglia));
		this.stanzaCorrente = this.mappa.get(stanza);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiacente, String direzione) {
		if(this.mappa.containsKey(stanzaCorrente)) {
			Stanza c = this.mappa.get(stanzaCorrente);
			Stanza a = this.mappa.get(stanzaAdiacente);
			c.impostaStanzaAdiacente(direzione, a);
			a.impostaStanzaAdiacente(this.direzioniOpposte.get(direzione), c);
		}
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.stanzaCorrente.addAttrezzo(attrezzo);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public Map<String,Stanza> getListaStanze(){
		return this.mappa;
	}
}