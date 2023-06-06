package it.uniroma3.diadia.personaggi;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_SALUTATO = "Che avventuriero gentile, per contraccambiare ti sposterò nella stanza con più attrezzi qui vicino\n"+
													 "Stai bene, ti senti stranito, ma la strega ha rispettato la promessa, c'è molto bottino per terra!";
	private static final String MESSAGGIO_NON_SALUTATO = "Che maleducato, neanche si saluta più?\n" + 
														 "Dopo un attimo di stordimento ti risvegli in una stanza diversa. Stai bene, ma purtroppo non c'è molto da prendere...";
	private static final String MESSAGGIO_REGALO = "AHAHAHAHAH, grazie mille avventuriero! \n La strega prende l'oggetto, chissà se lo rivedrai mai...";
	private Attrezzo attrezzo;
	private List <Stanza> listStanzeAdiacenti;

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		this.listStanzeAdiacenti = new ArrayList<Stanza>(partita.getStanzaCorrente().getMapStanzeAdiacenti().values());
		String msg;
		if (this.haSalutato()) {
			Stanza sMag = listStanzeAdiacenti.get(0);
			int attrMax = listStanzeAdiacenti.get(0).getNumeroAttrezzi();
			int i=1;
			Iterator <Stanza> it = listStanzeAdiacenti.iterator();
			it.next();
			while(it.hasNext()) {
				if(listStanzeAdiacenti.get(i).getNumeroAttrezzi() > attrMax) {
					sMag = listStanzeAdiacenti.get(i);
					attrMax = listStanzeAdiacenti.get(i).getNumeroAttrezzi();
				}
				i++;
				it.next();
			}
			partita.setStanzaCorrente(sMag);
			msg = MESSAGGIO_SALUTATO;
		}
		else {
			Stanza sMin = listStanzeAdiacenti.get(0);
			int attrMin = listStanzeAdiacenti.get(0).getNumeroAttrezzi();
			int i=1;
			Iterator <Stanza> it = listStanzeAdiacenti.iterator();
			it.next();
			while(it.hasNext()) {
				if(listStanzeAdiacenti.get(i).getNumeroAttrezzi() < attrMin) {
					sMin = listStanzeAdiacenti.get(i);
					attrMin = listStanzeAdiacenti.get(i).getNumeroAttrezzi();
				}
			}
			partita.setStanzaCorrente(sMin);
			msg = MESSAGGIO_NON_SALUTATO;
		}
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		this.attrezzo = attrezzo;
		return MESSAGGIO_REGALO;
	}
	
	public Attrezzo restituisciAttrezzo() {
		Attrezzo a = this.attrezzo;
		this.attrezzo = null;
		return a;
	}
}
