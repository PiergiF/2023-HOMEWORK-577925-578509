package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fabbricaDiComandiFisarmonicaTest;
	private IO io;

	@Before
	public void setUp() {
		fabbricaDiComandiFisarmonicaTest = new FabbricaDiComandiFisarmonica(io);
	}
	
	
//********************Test: esegui()********************
	
	@Test
	public void testCostruisciComandoComandoNonValido() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("boh").getNome(),"Non Valido");
	}
	
	@Test
	public void testCostruisciComandoComandoVai() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("vai").getNome(),"Vai");
	}
	
	@Test
	public void testCostruisciComandoComandoPrendi() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("prendi").getNome(),"Prendi");
	}
	
	@Test
	public void testCostruisciComandoComandoPosa() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("posa").getNome(),"Posa");
	}
	
	@Test
	public void testCostruisciComandoComandoAiuto() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("aiuto").getNome(),"Aiuto");
	}
	
	@Test
	public void testCostruisciComandoComandoFine() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("fine").getNome(),"Fine");
	}
	
	@Test
	public void testCostruisciComandoComandoGuarda() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("guarda").getNome(),"Guarda");
	}
}
