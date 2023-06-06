package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import it.uniroma3.diadia.*;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {

	private Partita partitaTest;
	private Labirinto labirinto;
	private ComandoVai comandoVai;
	private IOConsole io;
	private Stanza stanzaTest;
	private Stanza stanzaTest2;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.labirinto = new Labirinto("labirintoTest.txt");
		this.partitaTest = new Partita(this.labirinto);
		this.io = new IOConsole(new Scanner(System.in));
		this.comandoVai = new ComandoVai();
		this.comandoVai.setIOConsole(io);
		this.stanzaTest = new Stanza("stanzaTest");
		this.partitaTest.setStanzaCorrente(stanzaTest);
		this.stanzaTest2 = new Stanza("stanzaTest2");
	}
	
	@After
	public void closeScanner() {
		this.io.chiudiScanner();
	}
	
	//factory Methods
	public boolean StanzaAdiacente() {
		stanzaTest.impostaStanzaAdiacente("nord",stanzaTest2);
		comandoVai.setParametro("nord");
		return true;
	}
	
	public boolean NoStanzaAdiacente() {
		comandoVai.setParametro("sud");
		return true;
	}
	
	public boolean DirezioneAdiacenteSbagliata() {
		stanzaTest.impostaStanzaAdiacente("nord",stanzaTest2);
		comandoVai.setParametro("sud");
		return true;
	}
	
//********************Test: esegui()********************
	
	@Test
	public void testEseguiConStanzaAdiacente(){
		assertEquals(this.StanzaAdiacente(),comandoVai.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiSenzaStanzaAdiacente() {
		assertNotEquals(this.NoStanzaAdiacente(),comandoVai.esegui(partitaTest));
	}
	
	@Test
	public void testEseguiDirezioneAdiacenteSbagliata() {
		assertNotEquals(this.DirezioneAdiacenteSbagliata(),comandoVai.esegui(partitaTest));
	}
}
