package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	private String parametro;
	
	
	@Override
	public boolean esegui(Partita partita) {
		if(this.parametro == null) {
			this.getIO().mostraMessaggio("Che attrezzo vuoi prendere?");
			this.parametro = this.getIO().leggiRiga();
		}
		Attrezzo attrezzo = null;
		attrezzo = partita.getStanzaCorrente().getAttrezzo(this.parametro);
		if(attrezzo == null) {
			this.getIO().mostraMessaggio("oggetto non trovato\n");
			return false;
		}	
		else {
			if(partita.getGiocatore().setAttrezzo(attrezzo)) {
				if(partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
					this.getIO().mostraMessaggio("Attrezzo aggiunto alla borsa!\n");
					return true;
				}
				else {
					this.getIO().mostraMessaggio("Errore nella rimozione dell'oggetto\n");
					return false;
				}
			}
			else {
				this.getIO().mostraMessaggio("Raggiunto limite della borsa\n");
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
	public String getNome() {
		return "prendi";
	}

}