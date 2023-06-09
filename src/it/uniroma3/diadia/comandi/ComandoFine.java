package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando {

    private final static String MESSAGGIO = "Grazie di aver giocato!";
    
    
	public boolean esegui(Partita partita) {
    	partita.setFinita();
		this.getIO().mostraMessaggio(MESSAGGIO);
		return true;
	}
	
	public String getNome() {
		return "fine";
	}
}
