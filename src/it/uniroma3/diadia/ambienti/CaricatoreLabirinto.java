package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.eccezioni.*;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
//import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.io.*;
import java.util.*;

//il codice contiene vai metodi di prova commentati, così da migliorare il progetto in seguito

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";
	
	/* prefisso della riga contenente le caratteristiche delle stanze chiuse */
	private static final String STANZE_BLOCCATE_MARKER = "Bloccate: ";
	
	/* prefisso della riga contenente le caratteristiche delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Buie: ";
	
	/* prefisso della riga contenente le caratteristiche delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Magiche: ";
	
	/* prefisso della riga contenente le specifiche del mago */
	private static final String MAGO_MARKER = "Mago: ";

	/* prefisso della riga contenente le specifiche della strega */
	private static final String STREGA_MARKER = "Strega: ";
	
	/* prefisso della riga contenente le specifiche del cane */
	private static final String CANE_MARKER = "Cane: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	//private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private LabirintoBuilder builder;

	/*public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		//this.builder = labirintoBuilder;
	}*/
	
	public CaricatoreLabirinto(String nomeFile, LabirintoBuilder labirintoBuilder) throws FileNotFoundException {
		//this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = labirintoBuilder;
	}
	
//	public CaricatoreLabirinto(StringReader configurazione) throws FileNotFoundException, FormatoFileNonValidoException{
//		this.reader = new LineNumberReader(configurazione);
//		this.builder = new LabirintoBuilder(new Labirinto("labirintoTest.txt"));
//	}
	public CaricatoreLabirinto(StringReader configurazione) throws FileNotFoundException, FormatoFileNonValidoException{
		this.reader = new LineNumberReader(configurazione);
		this.builder = Labirinto.newBuilder("labirintoTest.txt");
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECreaStranzeBloccate();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaMago();
			this.leggiEImpostaStrega();
			this.leggiEImpostaCane();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			builder.addStanza(nomeStanza); //nomeStannza.trim()??
			//Stanza stanza = new Stanza(nomeStanza);
			//this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}
	
	private List<String> separaStringheAiPuntiEVirgola(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter("; ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		//builder.addStanzaIniziale(nomeStanzaIniziale);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		//builder.addStanzaVincente(nomeStanzaVincente);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		//builder.setStanzaIniziale(nomeStanzaIniziale);
		//builder.setStanzaVincente(nomeStanzaVincente);
		this.stanzaIniziale = builder.getMappaStanze().get(nomeStanzaIniziale);
		this.stanzaVincente = builder.getMappaStanze().get(nomeStanzaVincente);
		//builder.addStanzaIniziale(nomeStanzaIniziale);
		//builder.addStanzaVincente(nomeStanzaVincente);
		//this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		//this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}
	
	private void leggiECreaStranzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata : this.separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanzaBloccata = null;
			String direzioneBloccata = null;
			String oggettoSbloccante = null; 
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza bloccata."));
				nomeStanzaBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la direzione bloccata della stanza bloccata " + nomeStanzaBloccata + "."));
				direzioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'oggetto sbloccante della direzione " + direzioneBloccata + "della stanza bloccata " + nomeStanzaBloccata + "."));
				oggettoSbloccante = scannerLinea.next();
			}
			
			builder.addStanzaBloccata(nomeStanzaBloccata, direzioneBloccata, oggettoSbloccante);
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia : this.separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanzaBuia = null;
			String oggettoLuminoso = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza buia."));
				nomeStanzaBuia = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'oggeto luminoso della stanza buia " + nomeStanzaBuia + "."));
				oggettoLuminoso = scannerLinea.next();
			}
			
			builder.addStanzaBuia(nomeStanzaBuia, oggettoLuminoso);
		}
	}
	
	private void leggiECreaStanzeMagiche()throws FormatoFileNonValidoException {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificaStanzaMagica : this.separaStringheAlleVirgole(specificheStanzeMagiche)) {
			String nomeStanzaMagica = null;
			String stringaSogliaMagica = null;
			int sogliaMagica;
			try (Scanner scannerLinea = new Scanner(specificaStanzaMagica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza magica."));
				nomeStanzaMagica = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la soglia della stanza magica " + nomeStanzaMagica + "."));
				stringaSogliaMagica = scannerLinea.next();
				sogliaMagica = Integer.parseInt(stringaSogliaMagica);
			}
			
			builder.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		}
	}
	
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
			//Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			//this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	
/*	private void leggiEImpostaMago() throws FormatoFileNonValidoException {
		String specificheMago = this.leggiRigaCheCominciaPer(MAGO_MARKER);
		//for(String specificaPersonaggio : this.separaStringheAiPuntiEVirgola(specifichePersonaggi)) {
			String nomeMago = null;	
			String nomeAttrezzoDelMago = null;
			String stringaPesoAttrezzoDelMago;
			int pesoAttrezzoDelMago;
			String nomeStanzaMago = null;
			String presentazione = null;
			try (Scanner scannerLinea = new Scanner(specificheMago)) {
				scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago."));
				nomeAttrezzoDelMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del mago."));
				stringaPesoAttrezzoDelMago = scannerLinea.next();
				pesoAttrezzoDelMago = Integer.parseInt(stringaPesoAttrezzoDelMago);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire il mago."));
				nomeStanzaMago = scannerLinea.next();
				//scannerLinea.useDelimiter(".");//.toString();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago."));
				presentazione = scannerLinea.next();
				builder.addMago(nomeMago, nomeAttrezzoDelMago, pesoAttrezzoDelMago, nomeStanzaMago, presentazione);
			}
		//}
	}*/
	
//	private void leggiEImpostaMago() throws FormatoFileNonValidoException {
//		String specificheMago = this.leggiRigaCheCominciaPer(MAGO_MARKER);
//		//for(String specificaPersonaggio : this.separaStringheAiPuntiEVirgola(specifichePersonaggi)) {
//		if(!specificheMago.equals("no")) {
//			String nomeMago = null;	
//			String nomeAttrezzoDelMago = null;
//			String stringaPesoAttrezzoDelMago;
//			int pesoAttrezzoDelMago;
//			String nomeStanzaMago = null;
//			String presentazione = null;
//			try (Scanner scannerLinea = new Scanner(specificheMago)) {
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
//				nomeMago = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago."));
//				nomeAttrezzoDelMago = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del mago."));
//				stringaPesoAttrezzoDelMago = scannerLinea.next();
//				pesoAttrezzoDelMago = Integer.parseInt(stringaPesoAttrezzoDelMago);
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire il mago."));
//				nomeStanzaMago = scannerLinea.next();
//				scannerLinea.useDelimiter("\n");//.toString();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago."));
//				presentazione = scannerLinea.next();
//				builder.addMago(nomeMago, nomeAttrezzoDelMago, pesoAttrezzoDelMago, nomeStanzaMago, presentazione);
//			}
//		}
//		//}
//	}
	
	private void leggiEImpostaMago() throws FormatoFileNonValidoException {
		String specificheMago = this.leggiRigaCheCominciaPer(MAGO_MARKER);
		for(String specificaMago : this.separaStringheAiPuntiEVirgola(specificheMago)) {
			String nomeMago = null;	
			String nomeAttrezzoDelMago = null;
			String stringaPesoAttrezzoDelMago;
			int pesoAttrezzoDelMago;
			String nomeStanzaMago = null;
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago."));
				nomeAttrezzoDelMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del mago."));
				stringaPesoAttrezzoDelMago = scannerLinea.next();
				pesoAttrezzoDelMago = Integer.parseInt(stringaPesoAttrezzoDelMago);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire il mago."));
				nomeStanzaMago = scannerLinea.next();
//				scannerLinea.useDelimiter("\n");//.toString();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del mago."));
//				presentazione = scannerLinea.next();
				builder.addMago(nomeMago, nomeAttrezzoDelMago, pesoAttrezzoDelMago, nomeStanzaMago, Mago.PRESENTAZIONE);
			}
		}
	}
	
//	private void leggiEImpostaStrega() throws FormatoFileNonValidoException {
//		String specificheStrega = this.leggiRigaCheCominciaPer(STREGA_MARKER);
//		//for(String specificaPersonaggio : this.separaStringheAiPuntiEVirgola(specifichePersonaggi)) {
//			String nomeStrega = null;	
//			String nomeStanzaStrega = null;
//			String presentazione = null;
//			try (Scanner scannerLinea = new Scanner(specificheStrega)) {
//				scannerLinea.useDelimiter("; ");
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
//				nomeStrega = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire la strega."));
//				nomeStanzaStrega = scannerLinea.next();
//				//scannerLinea.useDelimiter(".");//.toString();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione della strega."));
//				presentazione = scannerLinea.next();
//				builder.addStrega(nomeStrega,nomeStanzaStrega, presentazione);
//			}
//		//}
//	}
	
	private void leggiEImpostaStrega() throws FormatoFileNonValidoException {
		String specificheStrega = this.leggiRigaCheCominciaPer(STREGA_MARKER);
		for(String specificaStrega : this.separaStringheAiPuntiEVirgola(specificheStrega)) {
		//if(!specificheStrega.equals("no")) {
			String nomeStrega = null;	
			String nomeStanzaStrega = null;
			//String presentazione = null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)){//specificheStrega)) {
				//scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire la strega."));
				nomeStanzaStrega = scannerLinea.next();
				//scannerLinea.useDelimiter(".");//.toString();
				/*check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione della strega."));
				presentazione = scannerLinea.next();*/
				builder.addStrega(nomeStrega,nomeStanzaStrega, Strega.PRESENTAZIONE);//, presentazione);
				//builder.addPersonaggio(Strega.class, nomeStrega, nomeStanzaStrega, 0, null, null);
			}
		//}
		}
	}
	
//	private void leggiEImpostaCane() throws FormatoFileNonValidoException {
//		String specificheCane = this.leggiRigaCheCominciaPer(CANE_MARKER);
//		//for(String specificaPersonaggio : this.separaStringheAiPuntiEVirgola(specifichePersonaggi)) {
//		if(!specificheCane.equals("no")) {
//			String nomeCane = null;	
//			String nomeAttrezzoDelCane = null;
//			String stringaPesoAttrezzoDelCane;
//			int pesoAttrezzoDelCane;
//			String nomeStanzaCane = null;
//			String presentazione = null;
//			try (Scanner scannerLinea = new Scanner(specificheCane)) {
//				scannerLinea.useDelimiter("; ");
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
//				nomeCane = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del cane."));
//				nomeAttrezzoDelCane = scannerLinea.next();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del cane."));
//				stringaPesoAttrezzoDelCane = scannerLinea.next();
//				pesoAttrezzoDelCane = Integer.parseInt(stringaPesoAttrezzoDelCane);
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire il cane."));
//				nomeStanzaCane = scannerLinea.next();
//				//scannerLinea.useDelimiter(".");//.toString();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del cane."));
//				presentazione = scannerLinea.next();
//				builder.addCane(nomeCane, nomeAttrezzoDelCane, pesoAttrezzoDelCane, nomeStanzaCane, presentazione);
//			}
//		}
//		//}
//	}
	
	private void leggiEImpostaCane() throws FormatoFileNonValidoException {
		String specificheCane = this.leggiRigaCheCominciaPer(CANE_MARKER);
		for(String specificaCane : this.separaStringheAiPuntiEVirgola(specificheCane)) {
			String nomeCane = null;	
			String nomeAttrezzoDelCane = null;
			String stringaPesoAttrezzoDelCane;
			int pesoAttrezzoDelCane;
			String nomeStanzaCane = null;
			//String presentazione = null;
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				//scannerLinea.useDelimiter("; ");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del cane."));
				nomeAttrezzoDelCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del cane."));
				stringaPesoAttrezzoDelCane = scannerLinea.next();
				pesoAttrezzoDelCane = Integer.parseInt(stringaPesoAttrezzoDelCane);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza dove inserire il cane."));
				nomeStanzaCane = scannerLinea.next();
//				//scannerLinea.useDelimiter(".");//.toString();
//				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del cane."));
//				presentazione = scannerLinea.next();
				builder.addCane(nomeCane, nomeAttrezzoDelCane, pesoAttrezzoDelCane, nomeStanzaCane, Cane.PRESENTAZIONE);
			}
		}
		//}
	}

	private boolean isStanzaValida(String nomeStanza) {
		return builder.getMappaStanze().containsKey(nomeStanza);
		//return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza;
			String dir;
			String stanzaDestinazione;
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {		
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				/*String*/ stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				/*String*/ dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				/*String*/ stanzaDestinazione = scannerDiLinea.next();
			}
			impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
		}
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		builder.addAdiacenza(stanzaDa, nomeA, dir);
		//Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		//Stanza arrivoA = this.nome2stanza.get(nomeA);
		//partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
