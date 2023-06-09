package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoSaluta extends AbstractComando {

	public static final String MESSAGGIO_NESSUN_PERSONAGGIO = "In questa stanza non c'è nessuno";
	
	@Override
	public boolean esegui(Partita partita) {
		if(partita.getStanzaCorrente().haPersonaggio())
			this.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().saluta());
		else
			this.getIO().mostraMessaggio(MESSAGGIO_NESSUN_PERSONAGGIO);
		return true;
	}
	
	@Override
	public String getNome() {
		return "saluta";
	}
}
