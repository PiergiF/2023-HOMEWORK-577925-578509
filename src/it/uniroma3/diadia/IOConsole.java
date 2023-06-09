package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
	
	private Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee = scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = this.scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
	
	public void chiudiScanner() {
		this.scannerDiLinee.close();
	}
}