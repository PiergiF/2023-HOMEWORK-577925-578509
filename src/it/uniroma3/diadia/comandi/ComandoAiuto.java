package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"aiuto", "vai", "prendi", "posa", "fine", "guarda", "saluta", "interagisci", "regala"};
	
	
	@Override
	public boolean esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.getIO().mostraMessaggio(elencoComandi[i]);
		return true;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	

}