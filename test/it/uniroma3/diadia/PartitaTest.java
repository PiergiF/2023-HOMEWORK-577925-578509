package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita partita;
	
	@Before
	public void setUp() {
		partita = new Partita();
	}
	
	//fartory methods
	public Partita giocatoreSenzaCfu() {
		partita.getGiocatore().setCfu(0);
		return partita;
	}
	
	public Partita giocatoreInStanzaVincente() {
		partita.setStanzaCorrente(partita.getLabirinto().getStanzaVincente());
		return partita;
	}
	
//********************Test: isFinita()********************
	
	@Test
	public void testIsFinitaSeStanzaVincente() {
		assertTrue(this.giocatoreInStanzaVincente().isFinita());
	}
	
	@Test
	public void testIsFinitaSeCfuFiniti() {
		assertTrue(this.giocatoreSenzaCfu().isFinita());
	}
	
	@Test
	public void testIsFinitaFalso() {
		assertFalse(partita.isFinita());
	}
	
//********************Test: vinta()********************
	
	@Test
	public void testVintaSeStanzaCorrenteIsVincente() {
		assertTrue(this.giocatoreInStanzaVincente().isFinita());
	}
	
	@Test
	public void testVintaSeStanzaCorrenteNotIsStanzaVincente() {
		assertFalse(partita.vinta());
	}
	
}
