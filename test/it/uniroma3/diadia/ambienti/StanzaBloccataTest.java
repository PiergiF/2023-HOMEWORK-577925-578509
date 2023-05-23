package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
		
	
	Stanza principale;
	Stanza adiacente;
	Attrezzo attrezzo;
	Attrezzo attrezzoSbloccante;
	
	
	@Before
	public void setUp(){
		principale = new StanzaBloccata("StanzaPrincipaleTest","nord","chiavi");
		adiacente = new Stanza("StanzaAdiacenteTest");
		attrezzo = new Attrezzo("spada",4);
		attrezzoSbloccante =new Attrezzo("chiavi",1);
		principale.impostaStanzaAdiacente("adiacente", adiacente);

	}
	
	//factory methods
	
	private Stanza testBloccataConOggettoNonSbloccante() {
		principale.addAttrezzo(attrezzo);
		return principale;
	}
	
	private Stanza testStanzaBloccataConOgettoSbloccante() {
		principale.addAttrezzo(attrezzoSbloccante);
		return principale;
	}
	private Stanza testStanzaBloccataSenzaOgetto(){
		return principale;
	}
	
	
	//************************** Test getDescrizione**********************************
	
	@Test
	public void testGetDescrizioneConOggettoNonSbloccante() {
		assertEquals(this.testBloccataConOggettoNonSbloccante().getDescrizione(),principale.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConOggettoSbloccante() {
		assertEquals(this.testStanzaBloccataConOgettoSbloccante().getDescrizione(),principale.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConNessunAttrezzo() {
		assertEquals(this.principale.getDescrizione(),principale.getDescrizione());
	}
	
//************************** Test getStanzaAdiacente**********************************
	
	@Test
	public void getStanzaAdiacenteConOggettoNonSbloccante() {
		assertEquals(this.testBloccataConOggettoNonSbloccante().getStanzaAdiacente("nord"),this.principale.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void getStanzaAdiacenteConOggettoSbloccante() {
		assertEquals(this.testStanzaBloccataConOgettoSbloccante().getStanzaAdiacente("nord"),this.principale.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testgetStanzaAdiacenteConNessunAttrezzo() {
		assertEquals(this.testStanzaBloccataSenzaOgetto().getStanzaAdiacente("nord"),principale.getStanzaAdiacente("nord"));
	}
}