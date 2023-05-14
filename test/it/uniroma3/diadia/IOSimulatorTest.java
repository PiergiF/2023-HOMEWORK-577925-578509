package it.uniroma3.diadia;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

public class IOSimulatorTest {

	private IOSimulator io;
	private List<String> comandi = new ArrayList<String>();
	
	@Test
	public void testComandoVuoto() {
		assertEquals("", new IOSimulator("").leggiRiga());
	}
	
	@Test
	public void testComandoNullo() {
		assertNull(new IOSimulator(null).leggiRiga());
	}
	
	@Test
	public void testUnSoloComando() {
		comandi.add("fine");
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}
	
	@Test
	public void testDueComandi() {
		io = new IOSimulator("vai sud", "fine");
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
	}
	
	@Test
	public void testTantiComandi() {
		io = new IOSimulator("vai sud", "fine","guarda","prendi chiave","aiuto" );
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("fine", io.leggiRiga());
		assertEquals("guarda", io.leggiRiga());
		assertEquals("prendi chiave", io.leggiRiga());
		assertEquals("aiuto", io.leggiRiga());
	}
}
