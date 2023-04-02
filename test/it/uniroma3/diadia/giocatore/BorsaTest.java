package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class BorsaTest {
	
	private Borsa borsaTest;
	private Attrezzo attrezzoTest1;
	private Attrezzo attrezzoTest2;
	private Partita partita;
	
	@Before
	public void setUp() {
		borsaTest = new Borsa(15);
		attrezzoTest1= new Attrezzo("spada",5);
		attrezzoTest2= new Attrezzo("libro",1);
		partita = new Partita();
	}
	
	public Borsa attrezziMax() {
		for(int i= 0; i< 10; i++)
			borsaTest.addAttrezzo(attrezzoTest2);
		return borsaTest;
	}
	
	
	public Borsa pesoMax() {
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest1);
		return borsaTest;
	}
	
	public Borsa borsaVuota() {
		return borsaTest;
	}
	
	
	
	//********************Test: addAttrezzo()********************
	
	@Test
	public void testAddAttrezzoBorsaPesoMax() {
		assertFalse(pesoMax().addAttrezzo(attrezzoTest1));
	}
	
	@Test
	public void testAddAttrezzoBorsaAttrezziMax() {
		assertFalse(attrezziMax().addAttrezzo(attrezzoTest1));
	}
	
	@Test
	public void testAddAttrezzoBorsavuota() {
		assertTrue(borsaVuota().addAttrezzo(attrezzoTest1));
	}
	
	//********************Test: getPeso()********************
	
	@Test
	public void testGetPesoBorsaPiena() {
		assertEquals(pesoMax().getPeso(), 15);
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(borsaVuota().getPeso(), 0);
	}
	
	//********************Test: getAttrezzo()********************
	
	@Test
	public void testGetAttrezzoBorsaVuota() {
		assertNull(borsaVuota().getAttrezzo("libro"));
	}
	
	@Test
	public void testGetAttrezzoBorsaPienaAttrezzoNonPresente() {
		assertNull(attrezziMax().getAttrezzo("spada"));
	}
	
	@Test
	public void testGetAttrezzoBorsaPienaAttrezzoPresente() {
		assertNotNull(attrezziMax().getAttrezzo("libro"));
	}
	
	@Test
	public void testGetAttrezzoNull() {
		assertNull(attrezziMax().getAttrezzo(null));
	}
	
	//********************Test: hasAttrezzo()********************

	@Test
	public void testHasAttrezzoBorsaPiena() {
		assertTrue(attrezziMax().hasAttrezzo("libro"));
	}
	public void testHasAttrezzoBorsaVuota() {
		assertFalse(attrezziMax().hasAttrezzo("libro"));
	}
	
	//********************Test: removeAttrezzo()********************
	
	@Test
	public void testRemoveAttrezzoAttrezzoPresente() {
		assertNotNull(pesoMax().removeAttrezzo("spada",partita));
	}
	@Test
	public void testRemoveAttrezzoAttrezzoNonPresente() {
		assertNull(pesoMax().removeAttrezzo("libro",partita));
	}
	@Test
	public void testRemoveAttrezzoBorsaPienaAttrezzoNull() {
		assertNull(attrezziMax().removeAttrezzo(null,partita));
	}
	
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(borsaVuota().removeAttrezzo("libro",partita));
	}
	
	
}