package it.uniroma3.diadia.difficolta;

import it.uniroma3.diadia.IO;

public class MenuDifficolta {

	private static final String MESSAGGIO_BENVENUTO = "Benvenuto in DiaDia, a che difficolta vuoi giocare?\n facile, media o difficile?";
	private IO io;
	
	public MenuDifficolta(IO io) {
		this.io = io;
	}
	
	public String scegliDifficolta() {
		
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		String difficoltaScelta;
		Difficolta difficolta;
		String output;
		
		boolean scelta = false;
		do {
			difficoltaScelta = io.leggiRiga();
			try {
			    String nomeClasse = "it.uniroma3.diadia.difficolta.Difficolta"; //compone la stringa con it.uniroma3.diadia.comandi.Comando
			    nomeClasse += Character.toUpperCase(difficoltaScelta.charAt(0)); //prende la prima lettera del comando inserita e la fa maiuscola e lo inserisce di seguito
			    nomeClasse += difficoltaScelta.substring(1); //prende il resto del comando e lo inserisce di seguito
				
			    difficolta=(Difficolta)Class.forName(nomeClasse).getConstructor().newInstance(); //getConstructor e' giusto??
			    output = difficolta.scegliFile();
			    scelta = true;
			}catch(Exception e) {
				io.mostraMessaggio("Questa difficoltà non è presente in DiaDia, riprova");
				output = "errore";
				//comando.setIOConsole(io);
				//this.io.mostraMessaggio("Comando inesistente");
			}
		}while(!scelta);
		    
		    return output;
	}
}
