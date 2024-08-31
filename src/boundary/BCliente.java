package boundary;
import control.GestioneSistema;
import exception.OperationException;


public class BCliente {
	public void prenotaVaccino(String nomeFarmacia, String giorno, String vaccino, String nome, String cognome, String email, String allergie){
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.prenotaVaccino(nomeFarmacia, giorno, vaccino, nome, cognome, email, allergie);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void cancellaAppuntamento(String emailCliente, String giorno, String emailFarmacia) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.cancellaAppuntamento(emailCliente, giorno, emailFarmacia);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
