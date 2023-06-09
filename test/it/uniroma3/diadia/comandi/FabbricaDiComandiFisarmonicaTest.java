package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiRiflessiva fabbricaDiComandiFisarmonicaTest;
	private IO io;

	@Before
	public void setUp() {
		fabbricaDiComandiFisarmonicaTest = new FabbricaDiComandiRiflessiva(io);
	}
	
	
//********************Test: esegui()********************
	
	@Test
	public void testCostruisciComandoComandoNonValido() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("boh").getNome(),"non valido");
	}
	
	@Test
	public void testCostruisciComandoComandoVai() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("vai").getNome(),"vai");
	}
	
	@Test
	public void testCostruisciComandoComandoPrendi() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("prendi").getNome(),"prendi");
	}
	
	@Test
	public void testCostruisciComandoComandoPosa() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("posa").getNome(),"posa");
	}
	
	@Test
	public void testCostruisciComandoComandoAiuto() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("aiuto").getNome(),"aiuto");
	}
	
	@Test
	public void testCostruisciComandoComandoFine() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("fine").getNome(),"fine");
	}
	
	@Test
	public void testCostruisciComandoComandoGuarda() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("guarda").getNome(),"guarda");
	}
	
	@Test
	public void testCostruisciComandoComandoInteragisci() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("interagisci").getNome(),"interagisci");
	}
	
	@Test
	public void testCostruisciComandoComandoRegala() {
		assertEquals(fabbricaDiComandiFisarmonicaTest.costruisciComando("regala").getNome(),"regala");
	}
}
