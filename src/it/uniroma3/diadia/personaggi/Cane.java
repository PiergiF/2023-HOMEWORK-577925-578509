package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	private static final String SALUTO_CON_CIBO = "Bau Bau <3";
	private static final String SALUTO_SENZA_CIBO = "grrrrrrr";
	private static final String MESSAGGIO_CON_CIBO = "Il cane è amorevole e ringrazia per il cibo scodinzolando e saltando";
	private static final String MESSAGGIO_SENZA_CIBO = "Ahi! Il cane ti ha morso e hai perso 1 CFU";
	private Attrezzo attrezzo;
	private boolean mangiato = false;

	public Cane(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String saluta() {
		if(mangiato)
			return SALUTO_CON_CIBO;
		else
			return SALUTO_SENZA_CIBO;
	}
	
	@Override
	public String agisci(Partita partita) {
		if (mangiato) 
			return MESSAGGIO_CON_CIBO;
		else {
			partita.getGiocatore().decrementaCfu();
			//StringBuilder string = new StringBuilder();
			//string.append(MESSAGGIO_SENZA_CIBO + partita.getGiocatore().getCfu());
			return MESSAGGIO_SENZA_CIBO + "\n" + partita.getGiocatore();
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder msg = new StringBuilder();
		if(attrezzo.getNome().equals("osso") || attrezzo.getNome().equals("carne")) {
			this.mangiato = true;
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			msg.append("Il cane per ringraziare ha lasciato " + this.attrezzo + "per terra");
			if(attrezzo.getNome().equals("osso")) {
				msg.append(" e si sdraia sgranocchiando l'osso");
			}
			else
				msg.append(" e si concede un pranzo con la carne che gli hai regalato");
		}
		else
			msg.append("Il cane ringhia indispettito...");
		return msg.toString();
	}
}