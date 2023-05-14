package it.uniroma3.diadia;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IOSimulator implements IO {
	private List<String> comandiLetti;
	private	Iterator<String> indiceComandiLetti;

	public IOSimulator(String ... comandiLetti) {
		if (comandiLetti != null) {
			this.comandiLetti = Arrays.asList(comandiLetti);
			this.indiceComandiLetti = this.comandiLetti.iterator();
		}
	}

	@Override
	public String leggiRiga() {
		if (this.indiceComandiLetti != null && this.indiceComandiLetti.hasNext())
			return this.indiceComandiLetti.next();
		return null;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		return ;
	}
	
	public int getNumeroComandi () {
		return this.comandiLetti.size();
	}
}
