package boundary;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import control.GestioneSistema;
import exception.OperationException;

public class BFarmacista {
	
	public boolean login(String u, String p) throws OperationException {
		if(u.length()==0 || u.length()>50) {
			throw new OperationException("Inserire un username valido");
		}
		if(p.length()==0 || p.length()>50) {
			throw new OperationException("Inserire una password valida");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		return gS.login(u,p);
	}
	
	public void stampaAppuntamenti(String usernameFarmacista, String data)throws OperationException, IOException {
		if(usernameFarmacista.length()==0 || usernameFarmacista.length()>50) {
			throw new OperationException("Inserire un username valido");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		LocalDate dataParsed= LocalDate.parse(data,formatter);
		
		gS.stampaAppuntamenti(usernameFarmacista, dataParsed);
	}
	
	//modifica per la gestione dell'esito
	public void inserisciDatiVaccinazione(String data,String usernameFarmacista ,String emailCliente, String esito, String motivazione) throws OperationException {
		if(usernameFarmacista.length()==0 || usernameFarmacista.length()>50) {
			throw new OperationException("Inserire un username valido");
		}
		if(emailCliente.length()==0 || emailCliente.length()>50  || !emailCliente.contains("@")) {
			throw new OperationException("Errore: inserire un e-mail valida...");
		}
		if(esito.equals("Negativo") && motivazione.length()==0) {
			throw new OperationException("Errore: inserire la motivazione della mancata vaccinazione...");
		}
//		System.out.println(motivazione.length());
//		System.out.println(esito.equals("Negativo") && motivazione.length()==0);
		
		
		GestioneSistema gS = GestioneSistema.getIstance();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		LocalDate dataParsed= LocalDate.parse(data,formatter);
//
//		if(dataParsed.isAfter(LocalDate.now())) {
//			throw new OperationException("Errore: inserire un giorno valido( Non puoi inserire i dati di una vaccinazione nel futuro!)...");
//		}
		gS.inserisciDatiVaccinazione(dataParsed,usernameFarmacista, emailCliente, esito, motivazione);
			

	}
	
	public void inserisciDatiPaziente(String datiAnamnestici, String emailCliente) throws OperationException {
		if(datiAnamnestici.length()==0 || datiAnamnestici.length()>100) {
			throw new OperationException("Errore: il numero dei caratteri inseriti per i dati anamnestici deve essere compreso tra 0 e 100...");
		}
		if(emailCliente.equals("") || emailCliente.length()>50 || !emailCliente.contains("@")) {
			throw new OperationException("Errore: inserire un e-mail valida...");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		gS.inserisciDatiPaziente(datiAnamnestici, emailCliente);

	}

}
