package it.uniroma3.diadia;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.*;
/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro
 * su cui si applica il comando.
 * (Ad es. alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version base
 */

public class Comando {
	private Partita partita;
	private Giocatore giocatore;
	private Borsa borsa;
    private String nome;
    private String parametro;
    static final private String[] elencoComandi = {"aiuto", "vai", "prendi", "posa", "fine"};
    
    public Comando(String istruzione, Partita partita ) {
		try (Scanner scannerDiParole = new Scanner(istruzione)) {
			// prima parola: nome del comando
			if (scannerDiParole.hasNext())
				this.nome = scannerDiParole.next(); 

			// seconda parola: eventuale parametro
			if (scannerDiParole.hasNext())
				this.parametro = scannerDiParole.next();

		}
		this.partita = partita;
		this.setGiocatore();
		this.setBorsa();
    }
    
    /**
	 * Restituisce il primo input dell'utente
	 * @return la prima parte dell'input dell'utente
	 */
    public String getNome() {
    	if(this.nome == null)
    		this.nome = "invalido";
    	
        return this.nome;
    }

    /**
	 * Restituisce il secondo input dell'utente
	 * @return la seconda parte dell'input dell'utente
	 */
    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {
        return (this.nome == null);
    }
    
    
    public void setGiocatore() {
		this.giocatore = partita.getGiocatore();
	}
    
    public void setBorsa() {
		this.borsa = giocatore.getBorsa();
	}
    
	//****INIZIO IMPLEMENTAZIONI DEI COMANDI DELL'UTENTE:****//

	/**
	 * Stampa informazioni di aiuto.
	 */
	public void aiuto(IOConsole console) {
		for(int i=0; i< elencoComandi.length; i++) 
			//System.out.print(elencoComandi[i]+" ");
			console.mostraMessaggio(elencoComandi[i]);
		//System.out.println();
		//console.mostraMessaggio();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	public void vai(IOConsole console) {
		if(this.parametro == null) {
			console.mostraMessaggio("Dove vuoi andare?");
			this.parametro = console.leggiRiga();
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(this.parametro);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente\n");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.giocatore.getCfu();
			this.giocatore.setCfu(--cfu);
		}
	}

	/**
	 * Comando "Fine".
	 */
	
	public void fine(IOConsole console) {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	
	/**
	 * comando che raccoglie, se c'e', un oggetto nella stanza corrente
	 * e lo inserisce nella borsa  
	 */
	public void raccogliAttrezzo (IOConsole console){
		if(this.parametro == null) {
			console.mostraMessaggio("Che attrezzo vuoi prendere?");
			this.parametro = console.leggiRiga();
		}
		Attrezzo attrezzo = null;
		attrezzo = this.partita.getStanzaCorrente().getAttrezzo(this.parametro);
		if(attrezzo == null)
			console.mostraMessaggio("oggetto non trovato\n");
		else {
			this.giocatore.setAttrezzo(attrezzo);
			if(this.partita.getStanzaCorrente().removeAttrezzo(attrezzo))
				console.mostraMessaggio("Attrezzo aggiunto alla borsa!\n");
			else
				console.mostraMessaggio("Errore nella rimozione dell'oggetto\n");
		}
	}
	
	/**
	 * comando che posa, se c'e', un oggetto nella stanza corrente
	 * e lo rimuove dalla borsa
	 */
	public void posaAttrezzo(IOConsole console) {
		if(this.parametro == null) {
			console.mostraMessaggio("Che attrezzo vuoi posare?\n");
			this.parametro = console.leggiRiga();
		}
		if(this.borsa.getAttrezzo(this.parametro) == null)
			console.mostraMessaggio("oggetto non trovato nella borsa\n");
		else {
			this.borsa.removeAttrezzo(this.parametro, partita);
			console.mostraMessaggio("Attrezzo rimosso alla borsa!\n");
		}
	}
}