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
		Stanza stanza = null;
		this.direzione=direzione;
		
		if (this.direzioneBloccata.equals(this.direzione)&& !this.hasAttrezzo(oggettoSbloccante)) {
			return stanza;
		}
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		String bloccata = "Non puoi accedere alla stanza:"+ direzioneBloccata  +"\nDevi avere le "+ oggettoSbloccante +" per entrare";

		if(!this.hasAttrezzo(oggettoSbloccante))
			return bloccata;
		return super.getDescrizione();
	}

	
}