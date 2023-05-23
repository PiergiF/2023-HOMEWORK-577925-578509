package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	private String oggettoLuminoso;
	
	public StanzaBuia(String nome,String oggettoLuminoso) {
		super(nome);
		this.oggettoLuminoso=oggettoLuminoso;
	}

	@Override
	public String getDescrizione() {
		String buia = "La stanza e' completamente buia:\nDevi avere una "+ oggettoLuminoso +" per poter guardare ";

		if(!this.hasAttrezzo(oggettoLuminoso))
			return buia;
		else 
		return super.getDescrizione();
	}
	
}