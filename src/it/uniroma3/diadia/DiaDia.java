package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;



public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	private IO io;
	private Partita partita;

	public DiaDia(IO console) {
		this.io = console;
		this.partita = new Partita();
		
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		do 	
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}
	
	public boolean processaIstruzione(String Istruzione) {

		Comando nuovoComando;
		FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica(this.io);
		nuovoComando = fabbrica.costruisciComando(Istruzione);
		nuovoComando.esegui(this.partita);	
		if(this.partita.vinta())
			io.mostraMessaggio("HAI VINTO NEGRO!!!");
			
		if (this.partita.getGiocatore().getCfu()==0)
			io.mostraMessaggio("SEI STIRATO ");
			
		return this.partita.isFinita();
	}
	
	
	

	public static void main(String[] argc) {
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}
