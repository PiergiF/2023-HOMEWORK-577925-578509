package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

	private IO io;
    private final static String MESSAGGIO = "Grazie di aver giocato!";
    
    
	@Override
	public boolean esegui(Partita partita) {
    	partita.setFinita();
		io.mostraMessaggio(MESSAGGIO);
		return true;
	}
	
	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}


	@Override
	public void setParametro(String parametro) {	
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public String getNome() {
		return "Fine";
	}
}
