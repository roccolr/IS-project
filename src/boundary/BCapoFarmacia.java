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
	
	public void registraFarmacista(String nome, String cognome, String nomeFarmacia, String emailFarmacista, boolean dipendente) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.registraFarmacista(nome, cognome, generaUsername(nome,cognome), generaPassword(nome,cognome), nomeFarmacia,emailFarmacista, dipendente);
		}catch (OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inserisciTurni() {}
	public void cancellaAppuntamento() {}
}
