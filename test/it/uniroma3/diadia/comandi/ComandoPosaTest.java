package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {

	private ComandoPosa comandoPosa;
	private Labirinto labirinto;
	private Partita partitaTest;
	private IO io;
	private Stanza stanzaTest;
	private Attrezzo attrezzoTest;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		partitaTest = new Partita(this.labirinto);
		io = new IOConsole();
		comandoPosa = new ComandoPosa();
		comandoPosa.setIOConsole(io);
		stanzaTest = new Stanza("stanzaTest");
		partitaTest.setStanzaCorrente(stanzaTest);
		attrezzoTest = new Attrezzo("martello",2);
		comandoPosa.setParametro("martello");
	}
	
	//Factory methods
	public boolean borsaConAttrezzo() {
		partitaTest.getGiocatore().getBorsa().addAttrezzo(attrezzoTest);
		return true;
	}
	
	public boolean stanzaPiena() {
		for(int i=0;i<10;i++) {
			stanzaTest.addAttrezzo(attrezzoTest);
		}
		return true;
	}
	
	public boolean borsaSenzaAttrezzi() {
		return true;
	}
	
	//********************Test: esegui()********************
	
	//Esistono già i test remove attrezzo e getAttrezzo nei test di borsa, sappiamo quindi che funzionano
	@Test
	public void testEseguiConAttrezzoInBorsa() {
		assertEquals(this.borsaConAttrezzo(),comandoPosa.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiConStanzaPiena() {
		assertNotEquals(this.stanzaPiena(),comandoPosa.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiConBorsaVuota() {
		assertNotEquals(this.borsaSenzaAttrezzi(),comandoPosa.esegui(partitaTest));
	}
}
