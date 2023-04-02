package it.uniroma3.diadia;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

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
	

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca(IOConsole console) {
		String istruzione;

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		do 	
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione,console));
	}
	
	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole console) {
		Comando comandoDaEseguire = new Comando(istruzione,partita);
		 
		if (comandoDaEseguire.getNome().equals("fine")) {
			comandoDaEseguire.fine(console); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai")) {
			comandoDaEseguire.vai(console);}
		
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			comandoDaEseguire.aiuto(console);
		else if (comandoDaEseguire.getNome().equals("prendi"))
			comandoDaEseguire.raccogliAttrezzo(console);
		else if (comandoDaEseguire.getNome().equals("posa"))
			comandoDaEseguire.posaAttrezzo(console);
		else
			console.mostraMessaggio("Comando sconosciuto");
		
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione());
		
		
		if (partita.isFinita()) {
			if (partita.vinta()) {
				console.mostraMessaggio("Hai vinto!");
				comandoDaEseguire.fine(console);
			}
			else {
				console.mostraMessaggio("Hai perso, sono finiti i tuoi cfu :(");
				comandoDaEseguire.fine(console);
			}
			return true;
		}else
			return false;
	}
	
	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia();
		gioco.gioca(console);
	}
}