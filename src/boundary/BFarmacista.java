package boundary;

import java.time.LocalDate;

import control.GestioneSistema;
import exception.OperationException;

public class BFarmacista {
	public void stampaAppuntamenti(String usernameFarmacista, LocalDate data) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.stampaAppuntamenti(usernameFarmacista, data);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inserisciDatiVaccinazione(String data,String usernameFarmacista ,String emailCliente, String esito, String motivazione) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.inserisciDatiVaccinazione(LocalDate.parse(data),usernameFarmacista, emailCliente, esito, motivazione);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inserisciDatiPaziente(String datiAnamnestici, String emailCliente) {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
			gS.inserisciDatiPaziente(datiAnamnestici, emailCliente);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void motivaVaccinazioneNonEffettuata() {}
	
}
