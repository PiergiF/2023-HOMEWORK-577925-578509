package it.uniroma3.diadia.simulazione;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simulazione {
	private Comando[] comandi;
	private Partita partita;
	private LabirintoBuilder labirintoBuilder;
	private IOConsole console;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		//labirintoBuilder = new LabirintoBuilder(new Labirinto("labirintoTest.txt"));
		this.labirintoBuilder = Labirinto.newBuilder("labirintoTest.txt");
		labirintoBuilder.addStanzaIniziale("inizio")
		.addAttrezzo("spada", 5)
		.addStanza("a sud di inizio")
		.addAdiacenza("inizio", "a sud di inizio", "sud")
		.addStanza("a est di a sud di inizio")
		.addAdiacenza("a sud di inizio", "a est di a sud di inizio", "est")
		.addStanzaBloccata("bloccata", "sud", "spada")
		.addAdiacenza("a sud di inizio", "bloccata", "ovest")
		.addStanzaVincente("fine")
		.addAdiacenza("inizio", "fine", "nord")
		.addAdiacenza("bloccata","fine", "sud");
		this.partita = new Partita(labirintoBuilder.getLabirinto());
		this.console = new IOConsole(new Scanner(System.in));
	}
	
	@After
	public void closeScanner() {
		this.console.chiudiScanner();
	}

	//*******************Factory Methods******************************************
	
	private void setComandi(String ... comandi) {
		IOSimulator io = new IOSimulator(comandi);
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(console);
		this.comandi = new Comando[io.getNumeroComandi()];
		for (int i = 0; i < io.getNumeroComandi(); i++) {
			this.comandi[i] = factory.costruisciComando(io.leggiRiga());
		}
	}
	
	private void eseguiComandi(Partita partita, Comando ... comandi) {
		for (int i = 0; i < comandi.length; i++) {
			this.comandi[i].esegui(partita);
		}
	}
	
	//*******************Simulazione******************************************
	
	@Test
	public void testComandiNonValidi() {
		setComandi("vai nord-est", "vai sud-ovest", "vai a destra e a sinistra",
					"saltella", "aiuto", "finE", "prendi ocarina", "prendi fionda",
					"vai sud-ovest", "posa masterSward");
		eseguiComandi(partita, this.comandi);
		assertEquals("inizio", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
		assertEquals(1, partita.getStanzaCorrente().getNumeroAttrezzi());
	}
	
	@Test
	public void testFinePartita() {
		setComandi("fine");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testVittoriaInFine() {
		setComandi("vai nord");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
		assertEquals(partita.getStanzaCorrente().getNome(), labirintoBuilder.getLabirinto().getStanzaVincente().getNome());
	}
	
	@Test
	public void testMovimento() {
		setComandi("vai sud", "vai est");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
		assertEquals("a est di a sud di inizio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	public void testRaccoltaOggetto() {
		setComandi("prendi spada");
		eseguiComandi(partita, this.comandi);
		assertEquals("inizio", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
	}
	
	@Test
	public void testPosaOggetto() {
		setComandi("prendi spada", "vai sud", "posa spada");
		eseguiComandi(partita, this.comandi);
		assertEquals("a sud di inizio", partita.getStanzaCorrente().getNome());
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testSbloccoStanzaBloccata() {
		setComandi("prendi spada", "vai sud", "vai ovest", "posa spada", "guarda stanza", "vai sud");
		eseguiComandi(partita, this.comandi);
		assertTrue(partita.isFinita());
	}
}
