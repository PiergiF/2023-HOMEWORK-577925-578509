package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando {

	private String parametro;
	
	@Override
	public boolean esegui(Partita partita) {
		
		if(partita.getStanzaCorrente().haPersonaggio()) {
			if(this.parametro == null) {
				if(partita.getStanzaCorrente().getPersonaggio().getNome().equals("strega"))
					this.getIO().mostraMessaggio("Cosa vuoi regalare alla " + partita.getStanzaCorrente().getPersonaggio()+"?");
				else
					this.getIO().mostraMessaggio("Cosa vuoi regalare al " + partita.getStanzaCorrente().getPersonaggio()+"?");
				this.parametro = this.getIO().leggiRiga();
			}
			if(partita.getGiocatore().getBorsa().isEmpty()) {
				this.getIO().mostraMessaggio("La borsa e' vuota");
				return false;
			}
			else {
				//AGGIUNGERE A BORSA MAPPA CHE RESTITUISCE PER NOME E IMPLEMENTARE QUI PER TROVARE L'ATTREZZO
				Attrezzo a = partita.getGiocatore().getBorsa().getContenutoRaggruppatoPerNome().get(this.parametro);
				if(a==null)
					return false;
				this.getIO().mostraMessaggio(partita.getStanzaCorrente().getPersonaggio().riceviRegalo(a, partita));
				partita.getGiocatore().getBorsa().removeAttrezzo(this.parametro, partita);
				return true;
			}
		}
		else {
			this.getIO().mostraMessaggio("Non c'è nessuno qui...");
			return false;
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
		return "regala";
	}
}
