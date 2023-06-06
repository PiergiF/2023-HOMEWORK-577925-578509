package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{

	public boolean esegui(Partita partita) {
		this.getIO().mostraMessaggio("Comando non valido");	
		return true;
	}
	
	public String getNome() {
		return "non valido";
	}
}