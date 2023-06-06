package it.uniroma3.diadia.comandi;

//import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ComandoPosaTest {

	private ComandoPosa comandoPosa;
	//private Labirinto labirinto;
	private Partita partitaTest;
	private IOConsole io;
	private Stanza stanzaTest;
	private Attrezzo attrezzoTest;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		//this.labirinto = new Labirinto();
		this.partitaTest = new Partita();//this.labirinto);
		this.io = new IOConsole(new Scanner(System.in));
		this.comandoPosa = new ComandoPosa();
		this.comandoPosa.setIOConsole(io);
		this.stanzaTest = new Stanza("stanzaTest");
		this.partitaTest.setStanzaCorrente(stanzaTest);
		this.attrezzoTest = new Attrezzo("martello",2);
		this.comandoPosa.setParametro("martello");
	}
	
	@After
	public void closeScanner() {
		this.io.chiudiScanner();
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
