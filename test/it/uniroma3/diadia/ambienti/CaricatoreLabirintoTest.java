package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {

	private final String monolocale = 
			"Stanze: biblioteca\n"+
			"Inizio: biblioteca\n"+
			"Vincente: biblioteca\n"+
			"Bloccate: \n"+
			"Buie: \n"+
			"Magiche: \n"+
			"Attrezzi: \n"+
			"Mago: no\n"+
			"Strega: no\n"+
			"Cane: no\n"+
			"Uscite: \n";

	private final String bilocale = 
			"Stanze: N12, N11\n"+
			"Inizio: N12\n"+
			"Vincente: N11\n"+
			"Bloccate: \n"+
			"Buie: \n"+
			"Magiche: \n"+
			"Attrezzi: martello 3 N12\n"+	
			"Mago: no\n"+
			"Strega: no\n"+
			"Cane: no\n"+
			"Uscite: \n";
	
	private final String labirinto =
			"Stanze: N10, N11\n"+
			"Inizio: N10\n"+
			"Vincente: N11\n"+
			"Bloccate: Laboratorio nord chiavi\n"+
			"Buie: Biblioteca torcia pinza\n"+
			"Magiche: Mensa 3\n"+
			"Attrezzi: martello 3 Laboratorio, forbici 2 N10\n"+
			"Mago: Bugo bacchetta 2 N10 Sono un mago, piacere di conoscerti.\n"+
			"Strega: Nistrid; Biblioteca; Oh, un avventuriero\n"+
			"Cane: Bob; forbice; 2; N11; grrrrrrr;\n"+
			"Uscite: \n";
	
	private CaricatoreLabirinto cl;

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(monolocale));
		cl.carica();
		assertEquals("biblioteca", this.cl.getStanzaIniziale().getNome());
		assertEquals("biblioteca", this.cl.getStanzaVincente().getNome());
		}
	
	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		assertEquals("N12", this.cl.getStanzaIniziale().getNome());
		assertEquals("N11", this.cl.getStanzaVincente().getNome());
		}
	
	
	@Test
	public void testBilocaleAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		Attrezzo expected = new Attrezzo("martello", 3);
		assertEquals(expected, this.cl.getStanzaIniziale().getAttrezzo("martello"));
		}
	
	@Test
	public void testLabirinto() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(labirinto));
		cl.carica();
		Attrezzo expected1 = new Attrezzo("forbici", 2);
		assertEquals(expected1, this.cl.getStanzaIniziale().getAttrezzo("forbici"));
		assertTrue(this.cl.getStanzaIniziale().haPersonaggio());
		}
}
