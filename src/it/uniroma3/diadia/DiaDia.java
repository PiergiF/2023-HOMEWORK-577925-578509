package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
//import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.difficolta.MenuDifficolta;



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

	public DiaDia(IO console, Labirinto labirinto) {
		this.io = console;
		this.partita = new Partita(labirinto);
		
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
		FabbricaDiComandiRiflessiva fabbrica = new FabbricaDiComandiRiflessiva(this.io);
		nuovoComando = fabbrica.costruisciComando(Istruzione);
		nuovoComando.esegui(this.partita);	
		if(this.partita.vinta())
			io.mostraMessaggio("HAI VINTO !!!");
			
		if (this.partita.getGiocatore().getCfu()==0)
			io.mostraMessaggio("SEI STIRATO ");
			
		return this.partita.isFinita();
	}
	
	
	

	public static void main(String[] argc) throws Exception {
		try (Scanner scanner = new Scanner(System.in)){
			IO console = new IOConsole(scanner);
			MenuDifficolta difficolta = new MenuDifficolta(console);
			//E se invocassi new Labirinto che invoca new LabirintoBuilder e CaricatoreLabirinto?
			//Labirinto labirinto = new LabirintoBuilder(difficolta.scegliDifficolta()).getLabirinto();
					/*.addStanzaIniziale("inizio")
					.addStanza("in_mezzo")
					.addAttrezzo("spada", 4)
					.addAdiacenza("inizio","in_mezzo" ,"sud" )
					.addStanzaMagica("magica", 3)
					.addAdiacenza("inizio","magica" ,"est" )
					.addStanzaBloccata("bloccata", "ovest", "spada")
					.addAdiacenza("inizio","bloccata" ,"ovest")
					.addStanzaVincente("fine")
					.addAdiacenza("inizio", "fine", "nord")
					.addAdiacenza("bloccata", "fine", "ovest")
					.getLabirinto();*/
			Labirinto labirinto = new Labirinto(difficolta.scegliDifficolta());
			//Labirinto labirinto =  Labirinto.newBuilder(difficolta.scegliDifficolta()).getLabirinto();
			DiaDia gioco = new DiaDia(console,labirinto);
			gioco.gioca();
		}
	}
}
