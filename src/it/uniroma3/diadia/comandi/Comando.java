package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public interface Comando {

	public boolean esegui(Partita partita);
	
	public void setParametro(String parametro);
	public String getParametro();
	public String getNome();
	
	public void setIOConsole(IO io);
	
}