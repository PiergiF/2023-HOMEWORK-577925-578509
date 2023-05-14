package it.uniroma3.diadia.ambienti;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

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
}