package control;
import exception.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import entity.*;
import database.*;



public class GestioneSistema {
	private static GestioneSistema gs = null;
	
	protected GestioneSistema() {}
	
	public GestioneSistema getIstance() {
		if(gs == null) {
			gs = new GestioneSistema();
		}
		return gs;
	}
	private static void inviaMail(String emailDestinario, String corpo) {}
	
	private static void inviaEmailCredenziali(CapoFarmacia cF, String emailCapoFarmacia) {
		String corpo = "Username: " + cF.getUsername() + " Password: " + cF.getPassword();
		inviaMail(emailCapoFarmacia, corpo);
	}
	
	private static void inviaEmailCredenziali(Farmacista f, String emailFarmacista) {
		String corpo = "Username: " + f.getUsername() + " Password: " + f.getPassword();
		inviaMail(emailFarmacista, corpo);
	}
	
	private static void inviaEmailRiepilogoPrenotazione(Cliente c, Prenotazione p, Farmacia f) {
		String corpo = "Prenotazione effettuata presso la farmacia: " + p.getNomeFarmacia() + " situata in " + f.getIndirizzo()
						+ "\nil giorno: " + p.getData().toString() + " alle ore: " + p.getOrario().toString()
						+ ";\n" + "Per disdire, l'email di riferimento e' : " + f.getEmail();
		inviaMail(c.getEmail(), corpo);
	}
	
	private static ArrayList<String> getEmailClienti()throws DAOException, DBConnectionException{
		ArrayList <String> listaEmailClienti = new ArrayList<>(); //lista da ritornare
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT EMAIL FROM CLIENTI;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					listaEmailClienti.add(r.getString(1));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Clienti...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return listaEmailClienti;
	}
	
	private static ArrayList<String> getNomiFarmacie() throws DAOException, DBConnectionException{
		ArrayList <String> nomiFarmacie = new ArrayList<>(); //lista da ritornare
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT NOME FROM FARMACIE;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					nomiFarmacie.add(r.getString(1));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacie...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return nomiFarmacie;
	}
	
	private static ArrayList<LocalTime> getOrari(LocalDate giorno, String nomeFarmacia)throws DAOException, DBConnectionException,OperationException{
		ArrayList<LocalTime> listaOrari = new ArrayList<>();
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT ORARIO FROM PRENOTAZIONI P JOIN VACCINAZIONI V ON P.CODICE = V.CODICEPRENOTAZIONE WHERE NOMEFARMACIA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeFarmacia);
				
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					listaOrari.add(LocalTime.parse(r.getString(1)));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Orari...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return listaOrari;
	}
	
	private static ArrayList<String> getUsernameCapiFarmacia()throws DAOException, DBConnectionException{
		ArrayList <String> usernameCapiFarmacia = new ArrayList<>(); //lista da ritornare
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT USERNAME FROM CAPIFARMACIA;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					usernameCapiFarmacia.add(r.getString(1));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura CapiFarmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return usernameCapiFarmacia;
	}
	
	private static ArrayList<String> getUsernameFarmacisti()throws DAOException, DBConnectionException{
		ArrayList <String> usernameFarmacisti = new ArrayList<>(); //lista da ritornare
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT USERNAME FROM FARMACISTI;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					usernameFarmacisti.add(r.getString(1));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacisti...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return usernameFarmacisti;
	}
	
 	public void prenotaVaccino(String nomeFarmacia, String giorno, String vaccino, String nome, String cognome, String email, String allergie)throws DAOException, DBConnectionException,OperationException {
		ArrayList<String> nomiFarmacie = null;
		ArrayList<LocalTime> listaOrari = null;
		ArrayList<String> emailClienti = null;
		LocalDate data = null;
		LocalTime orario = null;
		LocalTime maxOrario = LocalTime.of(20, 0, 0);
		Cliente c = null;
		Farmacia f = null;
		int codicePrenotazione;
		
		try {
			nomiFarmacie = getNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia");
		}
		
		//check sul nomeFarmacia
		if(!(nomiFarmacie.contains(nomeFarmacia))) {
			throw new OperationException("Errore: Farmacia non trovata...");
		}
		
		//si presuppone che la data sia già nel formato YYYY-MM-DD
		data = LocalDate.parse(giorno);
		if(data.isBefore(LocalDate.now())) {
			throw new OperationException("Errore: inserire un giorno valido...");
		}
		
		//check sul vaccino 
		if(!(vaccino.equals(Vaccino.PFISCHIO.toString()) || vaccino.equals(Vaccino.ASPERAZENZERO.toString()) || vaccino.equals(Vaccino.ANTIQUA.toString()))) {
			throw new OperationException("Errore: inserire un giorno valido...");
		}
		
		//Trova un orario disponibile per quel giorno
		listaOrari = getOrari(data, nomeFarmacia);
		if(listaOrari.isEmpty()) orario = LocalTime.of(8, 0, 0);
		else {
			listaOrari.sort(null);
			orario = listaOrari.get(listaOrari.size()-1).plusMinutes(15);
			if(orario.equals(maxOrario)) {
				throw new OperationException("Errore: non ci sono orari per il giorno richiesto...");
			}
		}
		
		Prenotazione p = new Prenotazione(data, orario, nomeFarmacia, email, vaccino);
		try {
			p.save();
			codicePrenotazione = p.getCodice();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio della prenotazione");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio della prenotazione");
		}
		
		Vaccinazione v = new Vaccinazione(codicePrenotazione);
		try {
			v.save();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio della vaccinazione");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio della vaccinazione");
		}
		
		try {
			emailClienti = getEmailClienti();
			if(!emailClienti.contains(email)) {
				c = new Cliente(nome, cognome, email, allergie);
				c.save();
			}
			else {
				c = ClienteDAO.readCliente(email);
			}
			
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio del Cliente");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del Cliente");
		}
		
		try {
			f = FarmaciaDAO.readFarmacia(nomeFarmacia);
			
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura della Farmacia");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura Farmacia");
		}
		
		inviaEmailRiepilogoPrenotazione(c, p, f);
	}
	
	public void registraCapoFarmacia(String nome, String cognome, String username, String password, String nomeFarmacia, String emailCapoFarmacia) throws DAOException, DBConnectionException,OperationException{
		ArrayList<String> nomiFarmacie = null;
		ArrayList<String> usernameCapiFarmacia = null;
		//si suppone che l'anagrafica del CapoFarmacia sia stata già controllata
		CapoFarmacia cF = new CapoFarmacia(nome, cognome, username, password);
		
		try {
			nomiFarmacie = getNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia");
		}
		
		if(!(nomiFarmacie.contains(nomeFarmacia))) throw new OperationException("Errore: La farmacia non memorizzata nel sistema");
		
		
		try {
			usernameCapiFarmacia = getUsernameCapiFarmacia();
			if(usernameCapiFarmacia.contains(cF.getUsername())) throw new OperationException("CapoFarmacia già presente nel sistema");
			else cF.save();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio del CapoFarmacia");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del CapoFarmacia");
		}
		
		try {
			AfferenzaDAO.createAfferernza(nomeFarmacia, cF.getUsername());
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio dell'afferenza");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio dell'afferenza");
		}
		
		inviaEmailCredenziali(cF, emailCapoFarmacia);
	}
	
	public void registraFarmacista(String nome, String cognome, String username, String password, String nomeFarmacia, String emailFarmacista, boolean dipendente) throws DAOException, DBConnectionException,OperationException{
		ArrayList<String> nomiFarmacie = null;
		ArrayList<String> usernameFarmacisti = null;
		Farmacista f = null;
		
		try {
			nomiFarmacie = getNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia");
		}
		
		if(!(nomiFarmacie.contains(nomeFarmacia))) throw new OperationException("Errore: La farmacia non memorizzata nel sistema");
		
		//si suppone che l'anagrafica del Farmacista sia stata già controllata
		f = new Farmacista(nome, cognome, username, password, dipendente, nomeFarmacia);
		try {
			usernameFarmacisti = getUsernameFarmacisti();
			if(usernameFarmacisti.contains(f.getUsername())) throw new OperationException("Farmacista già presente nel sistema");
			else f.save();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio del Farmacista");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del Farmacista");
		}
		
		inviaEmailCredenziali(f, emailFarmacista);	
	}
	
	
	public void cancellaAppuntamento() throws DAOException, DBConnectionException,OperationException{}
	public void inserisciTurniSettimana() throws DAOException, DBConnectionException,OperationException{}
	public void stampaAppuntamenti()throws DAOException, DBConnectionException,OperationException {}
	public void inserisciDatiVaccinazione() throws DAOException, DBConnectionException,OperationException{}
	public void motivaVaccinazioneNonEffettuata()throws DAOException, DBConnectionException,OperationException {}
	public void inviaReportStatistici()throws DAOException, DBConnectionException,OperationException {}
	public void inviaElencoTurni() throws DAOException, DBConnectionException,OperationException{}
	
}
