package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"aiuto", "vai", "prendi", "posa", "fine","guarda"};
	private IO io;
	
	
	@Override
	public boolean esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]);
		return true;
	}
	
	@Override
	public void setIOConsole(IO io) {
		this.io=io;
	}
	
	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setParametro(String parametro) {}

	@Override
	public String getNome() {
		return "Aiuto";
	}

	

}