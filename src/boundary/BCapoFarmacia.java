package boundary;
import control.GestioneSistema;
import exception.OperationException;
import java.util.Random;

public class BCapoFarmacia {
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
		String email = nome+cognome+r.nextInt(10)+"@italfarmaciefarmacisti.it";
		return email;
	}
	
	public void registraFarmacista(String nome, String cognome, String nomeFarmacia,boolean dipendente) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.registraFarmacista(nome, cognome, generaUsername(nome,cognome), generaPassword(nome,cognome), nomeFarmacia,generaEmail(nome, cognome), dipendente);
		}catch (OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void cancellaAppuntamento(int codicePrenotazione) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.cancellaAppuntamento(codicePrenotazione);
		}catch (OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void inserisciTurni(String nomeFarmacia) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.inserisciTurniSettimana(nomeFarmacia);
		}catch (OperationException e) {
			System.out.println(e.getMessage());
		}
	}
}
