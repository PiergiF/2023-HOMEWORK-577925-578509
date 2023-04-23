package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	
	Stanza principale;
	Attrezzo oggettoLuminoso;
	Attrezzo attrezzo;
	String buia = "La stanza e' completamente buia:\nDevi avere una torcia per poter guardare ";

	@Before
	public void setUp(){
		principale = new StanzaBuia("StanzaPrincipaleTest","torcia");
		oggettoLuminoso = new Attrezzo("torcia",4);
		attrezzo = new Attrezzo("spada",6);
		
	}
	
	//factory methods
	
	private Stanza testStanzaPrincipaleConAttrezzoNonLuminoso() {
		principale.addAttrezzo(attrezzo);
		return principale;
	}
	
	private Stanza testStanzaPrincipaleConAttrezzoLuminoso() {
		principale.addAttrezzo(oggettoLuminoso);
		return principale;
	}

	//************************** Test getDescrizione**********************************
	
	@Test
	public void testGetDescrizioneConAttrezzoNonLuminoso() {
		assertEquals(this.testStanzaPrincipaleConAttrezzoNonLuminoso().getDescrizione(),buia);
	}
	
	@Test
	public void testGetDescrizioneConAttrezzoLuminoso() {
		assertEquals(this.testStanzaPrincipaleConAttrezzoLuminoso().getDescrizione(),principale.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneConNessunAttrezzo() {
		assertEquals(this.principale.getDescrizione(),principale.getDescrizione());
	}
	
}