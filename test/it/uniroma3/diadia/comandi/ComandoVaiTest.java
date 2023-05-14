package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComandoVaiTest {

	private Partita partitaTest;
	private Labirinto labirinto;
	private ComandoVai comandoVai;
	private IO io;
	private Stanza stanzaTest;
	private Stanza stanzaTest2;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		partitaTest = new Partita(this.labirinto);
		io = new IOConsole();
		comandoVai = new ComandoVai();
		comandoVai.setIOConsole(io);
		stanzaTest = new Stanza("stanzaTest");
		partitaTest.setStanzaCorrente(stanzaTest);
		stanzaTest2 = new Stanza("stanzaTest2");
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
