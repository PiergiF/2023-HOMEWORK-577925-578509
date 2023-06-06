package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	Stanza principale;
	Stanza adiacente;
	Attrezzo attrezzo;

	@Before
	public void setUp(){
		principale = new Stanza("StanzaPrincipaleTest");
		adiacente = new Stanza("StanzaAdiacenteTest");
		attrezzo = new Attrezzo("spada",4);
	}

//factory methods
	
	private Stanza testAdiacenteIsAdiacenteAPrincipale() {
		principale.impostaStanzaAdiacente("adiacente", adiacente);
		return adiacente;
	}
	
	private Stanza testRiempiStanzaPrincipale() {
		for(int i=0; i<Stanza.getNumeroMassimoAttrezzi();i++) {
			principale.addAttrezzo(attrezzo);
		}
		return principale;
	}
	
	private Stanza testStanzaPrincipaleConAttrezzo() {
		principale.addAttrezzo(attrezzo);
		return principale;
	}
//********************Test: getStanzaAdiacente()********************
	@Test
	public void testGetStanzaAdiacenteCorretta() {
		assertSame(this.testAdiacenteIsAdiacenteAPrincipale(),principale.getStanzaAdiacente("adiacente"));
	}
	
	@Test
	public void testGetStanzaAdiacenteSbagliata() {
		assertNotSame(this.testAdiacenteIsAdiacenteAPrincipale(),principale.getStanzaAdiacente("non adiacente"));
	}
	
	public void testGetStanzaAdiacenteNull() {
		assertNotSame(this.testAdiacenteIsAdiacenteAPrincipale(),principale.getStanzaAdiacente(null));
	}

//********************Test: addAttrezzo()********************
	
	@Test
	public void testAddAttrezzoConStanzaSenzaAttrezzi(){
		assertTrue(principale.addAttrezzo(attrezzo));
	}

	@Test
	public void testAddAttrezzoConAttrezziPresenti(){
		assertTrue(principale.addAttrezzo(attrezzo));
	}
	
	@Test
	public void testAddAttrezzoConStanzaPiena(){
		assertFalse(this.testRiempiStanzaPrincipale().addAttrezzo(attrezzo));
	}

	//********************Test: hasAttrezzo()********************

	@Test
	public void testHasAttrezzoSePresente(){
		assertTrue(this.testStanzaPrincipaleConAttrezzo().hasAttrezzo("spada"));
	}

	@Test
	public void testHasAttrezzoSeStanzaVuota(){
		assertFalse(adiacente.hasAttrezzo("spada"));
	}

	@Test
	public void testHasAttrezzoSeStanzaConAttrezziMaSenzaIlCercato(){
		assertFalse(this.testStanzaPrincipaleConAttrezzo().hasAttrezzo("coltello"));
	}


//********************Test: getAttrezzo()********************

	@Test
	public void testGetAttrezzoSePresente(){
		assertEquals(attrezzo,this.testStanzaPrincipaleConAttrezzo().getAttrezzo("spada"));
	}

	@Test
	public void testGetAttrezzoSeNonPresente(){
		assertNull(principale.getAttrezzo("coltello"));
	}

	@Test
	public void testGetAttrezzoSeStanzaVuota(){
		assertNull(adiacente.getAttrezzo("spada"));
	}

//********************Test: removeAttrezzo()********************
	@Test
	public void testRemoveAttrezzoSePresente(){
		assertTrue(this.testStanzaPrincipaleConAttrezzo().removeAttrezzo(attrezzo));
	}
	
	public void testRemoveAttrezzoSeNonPresente(){
		assertFalse(adiacente.removeAttrezzo(attrezzo));
	}

}