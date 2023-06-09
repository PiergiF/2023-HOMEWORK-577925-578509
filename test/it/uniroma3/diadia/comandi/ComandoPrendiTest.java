package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Labirinto;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

public class ComandoPrendiTest {

	private ComandoPrendi comandoPrendi;
	private Labirinto labirinto;
	private Partita partitaTest;
	private IOConsole io;
	private Stanza stanzaTest;
	private Attrezzo attrezzoTest;
	private Attrezzo attrezzoTest2;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		//this.labirinto = new Labirinto("labirintoTest.txt");
		this.labirinto = Labirinto.newBuilder("labirintoTest.txt").getLabirinto();
		this.partitaTest = new Partita(this.labirinto);
		this.io = new IOConsole(new Scanner(System.in));
		
		this.comandoPrendi = new ComandoPrendi();
		this.comandoPrendi.setIOConsole(io);
		this.comandoPrendi.setParametro("martello");
		
		this.stanzaTest = new Stanza("stanzaTest");
		this.partitaTest.setStanzaCorrente(stanzaTest);
		
		this.attrezzoTest = new Attrezzo("martello",2);
		this.attrezzoTest2 = new Attrezzo("forbici",1);
	}
	
	@After
	public void closeScanner() {
		this.io.chiudiScanner();
	}

	//Factory Methods
	public boolean AttrezzoInStanza() {
		stanzaTest.addAttrezzo(attrezzoTest);
		return true;
	}
	
	public boolean StanzaPiena() {
		for(int i=0;i<10;i++) {
			stanzaTest.addAttrezzo(attrezzoTest);
		}
		return true;
	}
	
	public boolean StanzaVuota() {
		return true;
	}
	
	public boolean AttrezzoSbagliatoInStanza() {
		stanzaTest.addAttrezzo(attrezzoTest2);
		return true;
	}
	
	//********************Test: esegui()********************
	
	@Test
	public void testEseguiConAttrezzoInStanza() {
		assertEquals(this.AttrezzoInStanza(),comandoPrendi.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiConStanzaPiena() {
		assertEquals(this.StanzaPiena(),comandoPrendi.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiConStanzaVuota() {
		assertNotEquals(this.StanzaVuota(),comandoPrendi.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiConAttrezzoSbagliatoInStanza() {
		assertNotEquals(this.AttrezzoSbagliatoInStanza(),comandoPrendi.esegui(partitaTest));
	}
}
