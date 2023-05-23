package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private IO io;
	private String parametro;

	@Override
	public boolean esegui(Partita partita) {
		
		if(this.parametro == null) {
			io.mostraMessaggio("Dove vuoi andare?");
			this.parametro = io.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.parametro);
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente\n");
			return false;
		}
		else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = partita.getGiocatore().getCfu();
			partita.getGiocatore().setCfu(--cfu);
			io.mostraMessaggio(partita.getStanzaCorrente().getNomeStanza());
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
	public void setIOConsole(IO io) {
		this.io=io;
	}
	
	@Override
	public String getNome() {
		return "Vai";
	}

}