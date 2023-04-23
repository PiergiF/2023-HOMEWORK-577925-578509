package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {

	private String parametro;
	private IO io;
	
	
	@Override
	public boolean esegui(Partita partita) {
		if(this.parametro == null) {
			io.mostraMessaggio("Che attrezzo vuoi prendere?");
			this.parametro = io.leggiRiga();
		}
		Attrezzo attrezzo = null;
		attrezzo = partita.getStanzaCorrente().getAttrezzo(this.parametro);
		if(attrezzo == null) {
			io.mostraMessaggio("oggetto non trovato\n");
			return false;
		}	
		else {
			partita.getGiocatore().setAttrezzo(attrezzo);
			if(partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
				io.mostraMessaggio("Attrezzo aggiunto alla borsa!\n");
				return true;
			}
			else {
				io.mostraMessaggio("Errore nella rimozione dell'oggetto\n");
				return false;
			}
		}
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
	public void setIOConsole(IO io) {
		this.io=io;
	}
	
	@Override
	public String getNome() {
		return "Prendi";
	}

}