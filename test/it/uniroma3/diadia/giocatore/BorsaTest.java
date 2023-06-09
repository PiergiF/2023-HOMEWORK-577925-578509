package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class BorsaTest {
	
	private Borsa borsaTest;
	private Attrezzo attrezzoTest1;
	private Attrezzo attrezzoTest2;
	private Attrezzo attrezzoTest3;
	private Partita partita;
	private LabirintoBuilder labirintoBuilder;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		borsaTest = new Borsa(15);
		attrezzoTest1= new Attrezzo("spada",5);
		attrezzoTest2= new Attrezzo("libro",1);
		attrezzoTest3= new Attrezzo("martello",5);
		//labirintoBuilder = new LabirintoBuilder(new Labirinto("labirintoTest.txt"));
		this.labirintoBuilder = Labirinto.newBuilder("labirintoTest.txt");
		labirintoBuilder.addStanzaIniziale("inizio_test");
		partita = new Partita(labirintoBuilder.getLabirinto());
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
	
	public Borsa borsaConDueAttrezziStessoPeso() {
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest3);
		return borsaTest;
	}
	
	public Borsa borsaConDueAttrezziPesoDiverso() {
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest2);
		return borsaTest;
	}
	
	public Borsa borsaConDueAttrezziNomeDiverso() {
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest2);
		return borsaTest;
	}
	
	public Borsa borsaConDueAttrezziNomeUguale() {
		borsaTest.addAttrezzo(attrezzoTest1);
		borsaTest.addAttrezzo(attrezzoTest1);
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
	
	//********************Test: getSortedSetOrdinatoPerPeso()********************
	
	@Test
	public void testGetSortedSetOrdinatoPerPesoConPesoDiverso() {
		assertTrue(borsaConDueAttrezziPesoDiverso().getSortedSetOrdinatoPerPeso().iterator().next().getPeso() < borsaConDueAttrezziStessoPeso().getSortedSetOrdinatoPerPeso().last().getPeso());
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPesoConStessoPeso() {
		assertTrue(borsaConDueAttrezziStessoPeso().getSortedSetOrdinatoPerPeso().iterator().next().getNome().compareTo(borsaConDueAttrezziStessoPeso().getSortedSetOrdinatoPerPeso().last().getNome()) < 0);
	}
	
	//********************Test: getSortedSetOrdinatoPerPeso()********************
	
	@Test
	public void testGetContenutoOrdinatoPerPesoConPesoDiverso() {
		assertTrue(borsaConDueAttrezziPesoDiverso().getContenutoOrdinatoPerPeso().get(0).getPeso() < borsaConDueAttrezziStessoPeso().getContenutoOrdinatoPerPeso().get(1).getPeso());
	}
	
	@Test
	public void testGetContenutoOrdinatoPerPesoConStessoPeso() {
		assertTrue(borsaConDueAttrezziStessoPeso().getContenutoOrdinatoPerPeso().get(0).getNome().compareTo(borsaConDueAttrezziPesoDiverso().getContenutoOrdinatoPerPeso().get(1).getNome()) < 0);
	}
	
	//********************Test: getSortedSetOrdinatoPerPeso()********************
	
	@Test
	public void testGetContenutoOrdinatoPerNomeConNomeDiverso() {
		assertTrue(borsaConDueAttrezziNomeDiverso().getContenutoOrdinatoPerNome().first().getNome().compareTo(borsaConDueAttrezziNomeDiverso().getContenutoOrdinatoPerNome().last().getNome()) < 0);
	}
	
	@Test
	public void testGetContenutoOrdinatoPerNomeConNomeUguale() {
		assertTrue(borsaConDueAttrezziNomeUguale().getContenutoOrdinatoPerNome().first().getNome().compareTo(borsaConDueAttrezziNomeUguale().getContenutoOrdinatoPerNome().last().getNome()) == 0);
	}
	
	//********************Test: getSortedSetOrdinatoPerPeso()********************
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoConPesoDiverso() {
		assertNotSame(borsaConDueAttrezziPesoDiverso().getContenutoRaggruppatoPerPeso().get(this.attrezzoTest1.getPeso()),borsaConDueAttrezziPesoDiverso().getContenutoRaggruppatoPerPeso().get(this.attrezzoTest2.getPeso()));
	}
	
	@Test
	public void testGetContenutoRaggruppatoPerPesoConStessoPeso() {
		assertEquals(borsaConDueAttrezziStessoPeso().getContenutoRaggruppatoPerPeso().get(this.attrezzoTest1.getPeso()),borsaConDueAttrezziStessoPeso().getContenutoRaggruppatoPerPeso().get(this.attrezzoTest3.getPeso()));
	}
}