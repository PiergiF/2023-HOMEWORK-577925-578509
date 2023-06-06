package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoPosa extends AbstractComando {

	private String parametro;
	
	@Override
	public boolean esegui(Partita partita) {
		if(this.parametro == null) {
			this.getIO().mostraMessaggio("Che attrezzo vuoi posare?\n");
			this.parametro = this.getIO().leggiRiga();
		}
		if(partita.getGiocatore().getBorsa().getAttrezzo(this.parametro) == null) {
			this.getIO().mostraMessaggio("oggetto non trovato nella borsa\n");
			return false;
		}
		else {
			partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro, partita);
			this.getIO().mostraMessaggio("Attrezzo rimosso dalla borsa!\n");
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
	public String getNome() {
		return "posa";
	}

}