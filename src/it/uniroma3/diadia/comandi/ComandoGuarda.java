package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando {
	
	private IO io;
	private String parametro;

	@Override
	public boolean esegui(Partita partita) {
		
		if(this.parametro == null) {
			io.mostraMessaggio("Cosa vuoi guardare?");
			this.parametro = io.leggiRiga();
		}
		if(this.parametro.equals("stanza")) {
			io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			io.mostraMessaggio(partita.getGiocatore().getDescrizione());
		}
		else if (this.parametro.equals("borsa")) {
			io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		}
		else
			io.mostraMessaggio("La richiesta non è valida");
		
		/*io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		*io.mostraMessaggio(partita.getGiocatore().getDescrizione());
		*io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		*/
		return true;
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
		return "Guarda";
	}
}