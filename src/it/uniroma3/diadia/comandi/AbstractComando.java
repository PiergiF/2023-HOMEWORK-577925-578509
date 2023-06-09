package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;




public abstract class AbstractComando implements Comando {
	
	private IO io;	
	
	
	public void setIOConsole(IO io) {
		this.io =io;	
	}
	
	public IO getIO() {
		return this.io;
	}
	
	
	public void setParametro(String parametro) {}
	
	public String getParametro(){
		return null;
	}
	
	abstract public String getNome();

}
