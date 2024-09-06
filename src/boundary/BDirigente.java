package boundary;
import java.util.Random;

import control.GestioneSistema;
import exception.OperationException;


public class BDirigente {
	private static final String  username="dirigente31P";
	private static final String password="dirigente31Q";
	
	public boolean login(String u, String p) throws OperationException{
		if(!u.equals(username))throw new OperationException("Username non valido");
		if(!p.equals(password))throw new OperationException("Password errata!");
		return true;
	}
	
	private String generaUsername(String nome, String cognome) {
		Random r = new Random();
		String username = nome + cognome + r.nextInt(1000)+"CapoFarmacia";
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

	public void registraCapoFarmacia(String nome, String cognome,String nomeFarmacia)throws OperationException {
		if(nome.length()==0 || nome.length()>50) {
			throw new OperationException("Errore: inserire un nome valido...");
		}
		if(cognome.length()==0 || cognome.length()>50) {
			throw new OperationException("Errore: inserire un cognome valido...");
		}
		if(nomeFarmacia.length()==0 || nomeFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		gS.registraCapoFarmacia(nome, cognome, generaUsername(nome, cognome), generaPassword(nome, cognome), nomeFarmacia, generaEmail(nome, cognome));

	}
	
}
