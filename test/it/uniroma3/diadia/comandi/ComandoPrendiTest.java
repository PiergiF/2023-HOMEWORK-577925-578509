package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Labirinto;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private ComandoPrendi comandoPrendi;
	private Labirinto labirinto;
	private Partita partitaTest;
	private IO io;
	private Stanza stanzaTest;
	private Attrezzo attrezzoTest;
	private Attrezzo attrezzoTest2;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		partitaTest = new Partita(this.labirinto);
		io = new IOConsole();
		
		comandoPrendi = new ComandoPrendi();
		comandoPrendi.setIOConsole(io);
		comandoPrendi.setParametro("martello");
		
		stanzaTest = new Stanza("stanzaTest");
		partitaTest.setStanzaCorrente(stanzaTest);
		
		attrezzoTest = new Attrezzo("martello",2);
		attrezzoTest2 = new Attrezzo("forbici",1);
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
