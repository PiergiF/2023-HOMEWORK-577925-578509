package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando {
	
	private String parametro;

	@Override
	public boolean esegui(Partita partita) {
		
		if(this.parametro == null) {
			this.getIO().mostraMessaggio("Cosa vuoi guardare?");
			this.parametro = this.getIO().leggiRiga();
		}
		if(this.parametro.equals("stanza")) {
			this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			this.getIO().mostraMessaggio(partita.getGiocatore().getDescrizione());
		}
		else if (this.parametro.equals("borsa")) {
			this.getIO().mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		}
		else
			this.getIO().mostraMessaggio("La richiesta non è valida");
		
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
	public String getNome() {
		return "guarda";
	}
}