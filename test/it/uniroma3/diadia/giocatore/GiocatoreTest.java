package it.uniroma3.diadia.giocatore;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class GiocatoreTest {
	private Giocatore giocatoreTest;
	private Attrezzo attrezzo;

	@Before 
	public void setUp(){
		giocatoreTest = new Giocatore();
		attrezzo = new Attrezzo("spada",4);
	}
	
//Factory methods
	
	private int giocatoreConCfuIniziali() {
		return Giocatore.getCfuIniziali();
	}
	
	private Giocatore giocatoreConCfuAZero() {
		giocatoreTest.setCfu(0);
		return giocatoreTest;
	}

//********************Test: getCfu()********************
	@Test
	public void testGetCfuConCfuIniziali() {
		assertEquals(giocatoreTest.getCfu(),this.giocatoreConCfuIniziali());
	}
	
	@Test
	public void testGetCfuConCfuAZero() {
		assertEquals(this.giocatoreConCfuAZero().getCfu(),0);
	}

//********************Test: setCfu()********************
	@Test
	public void testSetCfu() {
		giocatoreTest.setCfu(5);
		assertEquals(giocatoreTest.getCfu(),5);
	}

//********************Test: getBorsa()********************
	@Test
	public void testGetBorsa () {
		assertNotNull(giocatoreTest.getBorsa());
	}

//********************Test: setAttrezzo()********************

	public void testSetAttrezzoValido(){
		 assertTrue(giocatoreTest.setAttrezzo(attrezzo));

	 }

}