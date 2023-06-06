package it.uniroma3.diadia.comandi;
import java.util.Scanner;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{
	
	private Scanner scannerDiParole;
	private IO io;

	
	public FabbricaDiComandiRiflessiva(IO io) {
		this.io=io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		
	    scannerDiParole = new Scanner(istruzione);
	    String nomeComando = null;
	    String parametro = null;
	    Comando comando = null;
	    
	    if (scannerDiParole.hasNext())
	    	nomeComando = scannerDiParole.next(); // prima parola: nome del comando

	    if (scannerDiParole.hasNext())
	    	parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
	    
	    try {
		    String nomeClasse = "it.uniroma3.diadia.comandi.Comando"; //compone la stringa con it.uniroma3.diadia.comandi.Comando
		    nomeClasse += Character.toUpperCase(nomeComando.charAt(0)); //prende la prima lettera del comando inserita e la fa maiuscola e lo inserisce di seguito
		    nomeClasse += nomeComando.substring(1); //prende il resto del comando e lo inserisce di seguito
			
		    comando=(Comando)Class.forName(nomeClasse).getConstructor().newInstance(); //getConstructor e' giusto??
		    comando.setIOConsole(io);
		    comando.setParametro(parametro);
	    }catch(NoClassDefFoundError n) {
	    	comando = new ComandoNonValido();
	    	comando.setIOConsole(io);
	    }
	    catch(Exception e) {
	    	comando = new ComandoNonValido();
	    	comando.setIOConsole(io);
	    	//this.io.mostraMessaggio("Comando inesistente");
	    }
	    
	    return comando;
	}

}