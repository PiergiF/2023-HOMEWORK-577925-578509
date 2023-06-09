package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String oggettoSbloccante;
	private String direzioneBloccata; 
	private String direzione;

	
	public StanzaBloccata ( String nome,String DirezioneBloccata,String OgettoSbloccante) {
		super(nome);
		this.direzioneBloccata=DirezioneBloccata;
		this.oggettoSbloccante=OgettoSbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = this; //aggiunto per rispettare i test. se direzione bloccata allora ritorna se stessa
		this.direzione=direzione;
		
		if (this.direzioneBloccata.equals(this.direzione)&& !this.hasAttrezzo(oggettoSbloccante)) {
			return stanza;
		}
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		//StringBuilder risultato = new StringBuilder();
		//String bloccata = "Non puoi accedere alla stanza:"+ direzioneBloccata  +"\nDevi avere le "+ oggettoSbloccante +" per entrare";

		if(!this.hasAttrezzo(oggettoSbloccante)) {
			StringBuilder risultato = new StringBuilder();
			String bloccata = "Non puoi accedere alla stanza: "+ direzioneBloccata  +" devi avere le "+ oggettoSbloccante +" per entrare";
			risultato.append(bloccata);
			risultato.append("\n Uscite consentite: ");
			for (String direzione : this.getDirezioni())
				if (direzione!=null && (!direzione.equals(this.direzioneBloccata)))
					risultato.append(" " + direzione);
			return risultato.toString();
		}
		return super.getDescrizione();
	}

}