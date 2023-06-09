package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	private String parametro;

	@Override
	public boolean esegui(Partita partita) {
		
		if(this.parametro == null) {
			this.getIO().mostraMessaggio("Dove vuoi andare?");
			this.parametro = this.getIO().leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.parametro);
		if (prossimaStanza == null || prossimaStanza.equals(partita.getStanzaCorrente())) { //aggiunto l'or per permettere a stanza bloccata di tornare se stessa se direzione bloccata
			this.getIO().mostraMessaggio("Direzione inesistente\n");
			return false;
		}
		else {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().decrementaCfu();
			this.getIO().mostraMessaggio(partita.getStanzaCorrente().getNomeStanza());
			this.getIO().mostraMessaggio(partita.getGiocatore().getDescrizione());
			return true;
		}
	}


	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}

	@Override
	public String getParametro() {
		return parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}

}