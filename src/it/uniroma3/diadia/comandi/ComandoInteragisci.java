package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.personaggi.*;

public class ComandoInteragisci extends AbstractComando {
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	
	public boolean esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			this.getIO().mostraMessaggio(this.messaggio);
			return true;
		}
		else {
			this.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
			return false;
		}
		
	}
	public String getMessaggio() {
		return this.messaggio;
	}
	
	@Override
	public String getNome() {
		return "interagisci";
	}
}