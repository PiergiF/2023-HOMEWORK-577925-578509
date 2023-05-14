package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoPosa implements Comando {

	private IO io;
	private String parametro;
	
	@Override
	public boolean esegui(Partita partita) {
		if(this.parametro == null) {
			io.mostraMessaggio("Che attrezzo vuoi posare?\n");
			this.parametro = io.leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().getAttrezzo(this.parametro) == null) {
			io.mostraMessaggio("oggetto non trovato nella borsa\n");
			return false;
		}
		else {
			partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro, partita);
			io.mostraMessaggio("Attrezzo rimosso dalla borsa!\n");
			return true;
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro= parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setIOConsole(IO io) {
		this.io=io;
	}
	
	@Override
	public String getNome() {
		return "Posa";
	}

}