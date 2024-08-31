package boundary;
import java.util.Random;

import control.GestioneSistema;
import exception.OperationException;
import java.util.Random;


public class Dirigente {
	private String generaUsername(String nome, String cognome) {
		Random r = new Random();
		String username = nome + cognome + r.nextInt(1000);
		return username;
	}
	
	private String generaPassword(String nome, String cognome) {
		Random r = new Random();
		String password = nome + cognome + r.nextInt(8000);
		return password;
	}
	
	private String generaEmail(String nome, String cognome) {
		Random r = new Random();
		String email = nome+cognome+r.nextInt(10)+"@italfarmacie.it";
		return email;
	}
	
	public void registraCapoFarmacia(String nome, String cognome,String nomeFarmacia) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.registraCapoFarmacia(nome, cognome, generaUsername(nome, cognome), generaPassword(nome, cognome), nomeFarmacia, generaEmail(nome, cognome));
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
