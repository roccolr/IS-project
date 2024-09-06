package boundary;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entity.Vaccino;
import control.GestioneSistema;
import exception.DBConnectionException;
import exception.OperationException;


public class  BCliente{
	public void prenotaVaccino(String nomeFarmacia, String data, Vaccino vaccino, String nome, String cognome, String email, String allergie)throws OperationException{
		GestioneSistema gS = GestioneSistema.getIstance();
//    	System.out.println("cioa");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		LocalDate dataParsed= LocalDate.parse(data,formatter);
//    	System.out.println(LocalDate.now());
//    	System.out.println(dataParsed);

    	
		if(dataParsed.isBefore(LocalDate.now())) {
			throw new OperationException("Errore: inserire un giorno valido...");
		}
		if(nomeFarmacia.length()==0 || nomeFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		if(nome.length()==0 || nome.length()>50) {
			throw new OperationException("Errore: inserire un nome valido...");
		}
		if(cognome.length()==0 || cognome.length()>50) {
			throw new OperationException("Errore: inserire un cognome valido...");
		}
		if(email.length()==0 || email.length()>50 || !email.contains("@")) {
			throw new OperationException("Errore: inserire un e-mail valida...");
		}
		if(allergie.length()>100) {
			throw new OperationException("Errore: un valore valido per le allergie...");
		}
//		if(false) {
//			throw new OperationException("Errore: inserire un  valido...");
//		}

		gS.prenotaVaccino(nomeFarmacia, dataParsed, vaccino, nome, cognome, email, allergie);
	}
	
	public void prenotaVaccino(String nomeFarmacia, String data, String vaccino, String nome, String cognome, String email, String allergie)throws OperationException{
		GestioneSistema gS = GestioneSistema.getIstance();
//    	System.out.println("ciao");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		LocalDate dataParsed= LocalDate.parse(data,formatter);
//    	System.out.println(LocalDate.now());
//    	System.out.println(dataParsed);
//    	System.out.println(nomeFarmacia);
//    	System.out.println(vaccino);
//    	System.out.println(nome);
//    	System.out.println(cognome);
//    	System.out.println(email);
//    	System.out.println(allergie);

    	
		if(dataParsed.isBefore(LocalDate.now())) {
			throw new OperationException("Errore: inserire un giorno valido...");
		}
		if(nomeFarmacia.length()==0 || nomeFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		if(nome.length()==0 || nome.length()>50) {
			throw new OperationException("Errore: inserire un nome valido...");
		}
		if(cognome.length()==0 || cognome.length()>50) {
			throw new OperationException("Errore: inserire un cognome valido...");
		}
		if(email.length()==0 || email.length()>50 || !email.contains("@")) {
			throw new OperationException("Errore: inserire un e-mail valida...");
		}
		if(allergie.length()>100) {
			throw new OperationException("Errore: un valore valido per le allergie...");
		}

		gS.prenotaVaccino(nomeFarmacia, dataParsed, Vaccino.valueOf(vaccino), nome, cognome, email, allergie);
	}
	
	public void cancellaAppuntamento(String emailCliente, String giorno, String emailFarmacia)throws OperationException {
		GestioneSistema gS = GestioneSistema.getIstance();
		try {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
			LocalDate dateParsed= LocalDate.parse(giorno,formatter);
			gS.cancellaAppuntamento(emailCliente, dateParsed, emailFarmacia);
		}catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
	}
}
