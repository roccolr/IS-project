package control;
import exception.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.*;
import entity.*;
import database.*;



public class GestioneSistema {
	private static GestioneSistema gs = null;
	
	protected GestioneSistema() {}
	
	public static GestioneSistema getIstance() {
		if(gs == null) {
			gs = new GestioneSistema();
		}
		return gs;
	}
	
	
	private static void inviaMail(String emailDestinatario, String corpo) {
		System.out.println(emailDestinatario + " ha ricevuto:\n\n" + corpo);
	}
	
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
	
	private static void inviaEmailDisdetta(String emailCliente, String emailFarmacia, int codicePrenotazione) {
		String corpo = "Comunicazione: Il cliente la cui email è " + emailCliente + " desidera disdire la prenotazione con codice " + codicePrenotazione;
		inviaMail(emailFarmacia, corpo);
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
		
	private static LocalTime findOrario(ArrayList<LocalTime> listaOrari) {
		ListIterator<LocalTime> iterator = listaOrari.listIterator();
		LocalTime previous= null;
		LocalTime current = null;
		long minutesBetween = 0;
		
		previous = iterator.next();
		while(iterator.hasNext()) {
			current = iterator.next();
			
			minutesBetween = Duration.between(previous, current).toMinutes();
			if(minutesBetween > 15) return previous.plusMinutes(15);
			
			previous = current;
		}
		
		//se arriva qui, non ha trovato buchi
		current = listaOrari.get(listaOrari.size()-1).plusMinutes(15);
		if(current.isAfter(LocalTime.parse("19:45:00"))) return null;
		else return current;
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
		//Lista di tutti i farmacisti impiegati in una delle farmacie della catena
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
	
	private static ArrayList<String> getUsernameFarmacisti(String nomeFarmacia)throws DAOException, DBConnectionException{
		//Lista di tutti i farmacisti impiegati in una delle farmacie della catena
		ArrayList <String> usernameFarmacisti = new ArrayList<>(); //lista da ritornare
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT USERNAME FROM FARMACISTI WHERE FARMACIA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeFarmacia);
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
	
	private static ArrayList<Turno> getTurni(LocalDate data)throws DAOException, DBConnectionException{
		ArrayList <Turno> turni = new ArrayList<>(); //lista da ritornare
		String d = data.toString();
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM TURNI WHERE DATA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, d);
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					turni.add(new Turno(r.getInt(3), LocalDate.parse(r.getString(2)), r.getString(4)));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacisti...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return turni;
	}
	
	
 	public void prenotaVaccino(String nomeFarmacia, String giorno, String vaccino, String nome, String cognome, String email, String allergie)throws OperationException {
		ArrayList<String> nomiFarmacie = null;
		ArrayList<LocalTime> listaOrari = null;
		ArrayList<String> emailClienti = null;
		LocalDate data = null;
		LocalTime orario = null;
//		LocalTime maxOrario = LocalTime.of(20, 0, 0);
		Cliente c = null;
		Farmacia f = null;
//		int codicePrenotazione;
		
		try {
			nomiFarmacie = getNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia; " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia; " + e.getMessage());
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
		try {
			listaOrari = getOrari(data, nomeFarmacia);
		}catch(DBConnectionException e){
			throw new OperationException("Getting lista orari on DB:" + e.getMessage());
		}catch(DAOException e) {
			throw new OperationException("Getting lista orari: "+e.getMessage());
		}
		if(listaOrari.isEmpty()) orario = LocalTime.of(8, 0, 0);
		else {
			listaOrari.sort(null);
//			orario = listaOrari.get(listaOrari.size()-1).plusMinutes(15);
			
			orario = findOrario(listaOrari);
			if(orario == null) throw new OperationException("Errore: nessun orario trovato per quel giorno...");
		}
		
		//eventuale salvataggio del cliente
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
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		Prenotazione p = new Prenotazione(data, orario, nomeFarmacia, email, vaccino);
		try {
			if(p.isAlreadySaved()) throw new OperationException("Errore: Prenotazione già salvata nel sistema");
			else{
				p.save();
//				codicePrenotazione = p.getCodice();
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(OperationException e) {
			throw new OperationException(e.getMessage());
		}
		
//		System.out.println("debug");
		try {
			Vaccinazione v = new Vaccinazione(p.getCodice(), p.getNomeFarmacia());
			v.save();
//			System.out.println("debug");
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		try {
			f = FarmaciaDAO.readFarmaciaFromNome(nomeFarmacia);
			
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		inviaEmailRiepilogoPrenotazione(c, p, f);
		
		//aggiornamento della CartellaStatistica relativa alla farmcia
		try {
			CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(nomeFarmacia);
			cS.incrementaPrenotazioni();
			cS.save();
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		
	}
	
	public void registraCapoFarmacia(String nome, String cognome, String username, String password, String nomeFarmacia, String emailCapoFarmacia) throws OperationException{
		ArrayList<String> nomiFarmacie = null;
		ArrayList<String> usernameCapiFarmacia = null;
		//si suppone che l'anagrafica del CapoFarmacia sia stata già controllata
		CapoFarmacia cF = new CapoFarmacia(nome, cognome, username, password);
		
		try {
			nomiFarmacie = getNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia " + e.getMessage());
		}
		
		if(!(nomiFarmacie.contains(nomeFarmacia))) throw new OperationException("Errore: La farmacia non memorizzata nel sistema");
		
		
		try {
			usernameCapiFarmacia = getUsernameCapiFarmacia();
			if(usernameCapiFarmacia.contains(cF.getUsername())) throw new OperationException("CapoFarmacia già presente nel sistema");
			else cF.save();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio del CapoFarmacia "+e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del CapoFarmacia " + e.getMessage());
		}
		
		try {
			AfferenzaDAO.createAfferernza(nomeFarmacia, cF.getUsername());
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio dell'afferenza " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio dell'afferenza " + e.getMessage());
		}
		
		inviaEmailCredenziali(cF, emailCapoFarmacia);
	}
	
	public void registraFarmacista(String nome, String cognome, String username, String password, String nomeFarmacia, String emailFarmacista, boolean dipendente) throws OperationException{
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
			throw new OperationException("Errore durante il salvataggio del Farmacista " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del Farmacista");
		}
		
		inviaEmailCredenziali(f, emailFarmacista);	
	}
	
	//Il metodo cancellaAppuntamento è sovraccaricato: Uno è usato da Cliente, uno da CapoFarmacia
	public void cancellaAppuntamento(String emailCliente, String giorno, String emailFarmacia) throws OperationException{
		Farmacia f = null;
		try {
			f = FarmaciaDAO.readFarmaciaFromEmail(emailFarmacia);
		}catch(DAOException e) {
			throw new OperationException("Errore durante la cancellazione dell'appuntamento: " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la cancellazione dell'appuntamento: " + e.getMessage());
		}
		
		LocalDate data = LocalDate.parse(giorno);
		ArrayList<String> listaEmailClienti = null;
		int codice;
		
		if (f == null) throw new OperationException("Errore: Nessuna farmacia trovata associata all'email indicata");
		
		//check emailCliente
		try {
			listaEmailClienti = getEmailClienti();
			try {
				if(!listaEmailClienti.contains(emailCliente)) throw new OperationException("Errore, l'email indicata non è valida...");
			}catch(OperationException e) {
				throw e;
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e){
			throw new OperationException(e.getMessage());
		}
		
		
		//ATTENZIONE: Il sistema deve controllare che un cliente non faccia la stessa prenotazione più volte in  
		//farmacie diverse: controlla la funzione prenotaVaccino()
		
		try {
			codice = PrenotazioneDAO.getCodice(data, emailCliente);
//			System.out.println("debug");
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e){
			throw new OperationException(e.getMessage());
		}
		
		inviaEmailDisdetta(emailCliente, emailFarmacia, codice);
	}
	
	public void cancellaAppuntamento(int codicePrenotazione) throws OperationException{
		String nomeFarmacia = null;
		
		try {
			Prenotazione p = PrenotazioneDAO.readPrenotazione(codicePrenotazione);
			nomeFarmacia = p.getNomeFarmacia();
			int codiceVaccinazione = VaccinazioneDAO.getCodice(codicePrenotazione);
			VaccinazioneDAO.deleteVaccinazione(codiceVaccinazione);
			PrenotazioneDAO.deletePrenotazione(codicePrenotazione);
//			System.out.println("debug");
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		try {
			CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(nomeFarmacia);
			cS.incrementaAnnullamenti();
			cS.save();
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
	}
	
	
	public void inserisciTurniSettimana(String nomeFarmacia) throws OperationException{
		try {	
			LocalDate today = LocalDate.now();
			LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			LocalDate giorno = nextMonday;
			
			ArrayList <String> nomiFarmacie = getNomiFarmacie();
			if(!(nomiFarmacie.contains(nomeFarmacia)))throw new OperationException("Errore inserimento turno: La farmacia non esiste...");
			
			ArrayList <String> usernameFarmacisti = getUsernameFarmacisti(nomeFarmacia); 
			
			Scanner scanner = new Scanner(System.in);
			try {
				for(int i=0; i<14; i++) {
					if(i%2 == 0 && i!= 0) giorno=giorno.plusDays(1);
					Turno t = new Turno(0, giorno, null);
					if(i%2 == 0) {
						t.setTipo(0);
						System.out.println("Inserire farmacista per il turno di mattina del " + t.getData());
					}
					else {
						t.setTipo(1);
						System.out.println("Inserire farmacista per il turno di pomeriggio/sera del " + t.getData());
					}
					String usernameFarmacista = scanner.nextLine();
					if(!(usernameFarmacisti.contains(usernameFarmacista))) throw new OperationException("Errore: Farmacista non appartenente alla farmacia");
					t.setUsernameFarmacista(usernameFarmacista);
					t.save();
				}
			}catch(NoSuchElementException e) {
				throw new OperationException("Errore con l'input da tastiera...");
			}finally {
				scanner.close();
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
	}
	
//	public void inserisciTurniSettimana(String nomeFarmacia, HashMap<Turno, String> turniNextWeek) throws DAOException, DBConnectionException,OperationException{
//		//un CapoFarmacia, per ogni turno della settimana successiva, assegna un Farmacista della farmacia a cui afferisce.
//		//La map K = Username farmacista, V Lista di turni a lui associata
//		Farmacia f = null;
//		String usernameCapoFarmacia = null;
//		ArrayList<String> usernameFarmacisti = null; 
//		ArrayList<Farmacista> farmacisti = new ArrayList<>();
//		CapoFarmacia cF = null;
//		
//		
//		try {
//			f = FarmaciaDAO.readFarmaciaFromNome(nomeFarmacia);
//			usernameCapoFarmacia = AfferenzaDAO.readCapoFarmaciaFromFarmacia(f.getNome());
//			cF = CapoFarmaciaDAO.readCapoFarmacia(usernameCapoFarmacia);
//			f.setCapoFarmacia(cF);
//			usernameFarmacisti = getUsernameFarmacisti(f.getNome());
//			for(ListIterator<String> iterator = usernameFarmacisti.listIterator(); iterator.hasNext();) {
//				farmacisti.add(FarmacistaDAO.readFarmacista(iterator.next()));
//			}
//			f.setFarmacisti(farmacisti);
//			
//			for(Farmacista farmacista : f.getFarmacisti()) {
//				farmacista.setTurni(turniNextWeek.get(farmacista.getUsername()));
//				for(Turno t : farmacista.getTurni()) {
//					t.save();
//				}
//			}
//			
//		}catch(DAOException e) {
//			throw new OperationException(e.getMessage());
//		}
//	}
	
	public void stampaAppuntamenti(String usernameFarmacista, LocalDate data)throws OperationException {
		ArrayList<Turno> turni = null;
		try {
			turni = getTurni(data);
			
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		TreeMap<Prenotazione, ArrayList<String>> daStampare = new TreeMap<>();
		String orarioPrimoAppuntamentoMattina = "08:00:00";
		String orarioUltimoAppuntamentoMattina = "13:45:00";
		String orarioPrimoAppuntamentoSera= "14:00:00";
		String orarioUltimoAppuntamentoSera = "19:45:00";
		int tipo = -1;
		for(Turno t : turni) {
			if(t.getUsernameFarmacista().equals(usernameFarmacista)) {
				tipo = t.getTipo();
			}
		}
		try {
			try {
				Connection conn = DBManager.getConnection();
				String query = "SELECT P.DATA, P.ORARIO, V.NOMEFARMACIA, P.EMAILCLIENTE, P.VACCINO, C.NOME, C.COGNOME " + 
							   "FROM PRENOTAZIONI P JOIN CLIENTI C ON C.EMAIL = P.EMAILCLIENTE " +
							   "JOIN VACCINAZIONI V ON P.CODICE = V.CODICEPRENOTAZIONE " +
							   "WHERE DATA = ? AND ORARIO BETWEEN ? AND ? " +
							   "ORDER BY P.ORARIO;";
				try {
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, data.toString());
					if(tipo == 0) {
						stmt.setString(2, orarioPrimoAppuntamentoMattina);
						stmt.setString(3, orarioUltimoAppuntamentoMattina);
					}
					else {
						stmt.setString(2, orarioPrimoAppuntamentoSera);
						stmt.setString(3, orarioUltimoAppuntamentoSera);
					}
					ResultSet r = stmt.executeQuery();
					while(r.next()) {
						ArrayList<String> temp = new ArrayList<>();
						temp.add(r.getString(6));
						temp.add(r.getString(7));
						LocalDate d = LocalDate.parse(r.getString(1));
						LocalTime o = LocalTime.parse(r.getString(2));
						String nomeFarmacia = r.getString(3);
						String emailCliente = r.getString(4);
						String nomeVaccino = r.getString(5);
						daStampare.put(new Prenotazione(d,o,nomeFarmacia,emailCliente,nomeVaccino), temp);
						System.out.println("debug");
						

					}
				}catch(SQLException e) {
					throw new DAOException("Errore lettura Prenotazioni " + e.getMessage() + "\n" + e.getSQLState());
				}finally {
					DBManager.closeConnection();
				}
			}catch(SQLException e) {
				throw new OperationException("Errore connessione database...");
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		if(daStampare.isEmpty()) System.out.println("Non c'è nessun appuntamento per il giorno selezionato...");
		else {
			for(Map.Entry<Prenotazione, ArrayList<String>> entry : daStampare.entrySet()) {
				String giorno = entry.getKey().getData().toString();
				String orario = entry.getKey().getOrario().toString();
				String vaccino = entry.getKey().getNomeVaccino();
				String nomeCliente = entry.getValue().get(0);
				String cognomeCliente = entry.getValue().get(1);
				
				System.out.println("Prenotazione per il " + giorno + " alle " + orario + "; effettuare vaccino " + vaccino + " al cliente " + nomeCliente + " " + cognomeCliente);
			}
		}
	}
	
	public void inserisciDatiPaziente(String datiAnamnestici, String emailCliente) throws OperationException{
		Cliente c=null;
		try {
			c = ClienteDAO.readCliente(emailCliente);
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		c.setDatiAnamnestici(datiAnamnestici);
		try {
			ClienteDAO.updateCliente(c, emailCliente);
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
	}
	
	public void inserisciDatiVaccinazione(LocalDate data,String usernameFarmacista ,String emailCliente, String esito, String motivazione) throws OperationException{
		int codicePrenotazione, codiceVaccinazione;
		Prenotazione p = null;
		Vaccinazione v = null;
		//Il check su esito/motivazione viene fatto a monte
		try {
			codicePrenotazione = PrenotazioneDAO.getCodice(data, emailCliente);
			codiceVaccinazione = VaccinazioneDAO.getCodice(codicePrenotazione);
//			System.out.println(codicePrenotazione + " " + codiceVaccinazione);
			Farmacista f = FarmacistaDAO.readFarmacista(usernameFarmacista);
//			System.out.println(f.getNome() + " " +f.getCognome() + f.getNomeFarmacia());
			p  = PrenotazioneDAO.readPrenotazione(codicePrenotazione);
//			System.out.println(p.getCodice() + " " + p.getEmailCliente() + " " + p.getData());
			v = new Vaccinazione(codicePrenotazione, f.getNomeFarmacia());
			v.setCodice(codiceVaccinazione);
			v.setEsito(esito);
			v.setMotivazione(motivazione);
			v.setUsernameFarmacista(usernameFarmacista);
//			System.out.println(v.getCodice() + " " + v.getCodicePrenotazione() + " " + v.getNomeFarmacia());
			VaccinazioneDAO.updateVaccinazione(v, codiceVaccinazione);
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		if(v.getEsito().equalsIgnoreCase("positivo")) {
			try {
				CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(p.getNomeFarmacia());
				cS.incrementaVaccinazioni();
				cS.save();
			}catch(DAOException e) {
				throw new OperationException(e.getMessage());
			}catch(DBConnectionException e) {
				throw new OperationException(e.getMessage());
			}
		}
	}
	
	//public void motivaVaccinazioneNonEffettuata()throws DAOException, DBConnectionException,OperationException {}
	//commentata perchè viene gestito in inserisciDAtiVaccinazione
	
	
	public void inviaReportStatisticoGiornaliero(String emailImpiegato)throws OperationException {
		ArrayList <String> nomiFarmacie = null;
		String corpo = "Report statistico giornaliero: \n\n";
		try {
			nomiFarmacie = getNomiFarmacie();
			for(String nomeFarmacia : nomiFarmacie) {
				CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(nomeFarmacia);
				corpo = corpo.concat("Farmacia: " + cS.getNomeFarmacia());
				corpo = corpo.concat("\nNumero prenotazioni giornaliere: " + cS.getNumeroPrenotazioniGiornaliere());
				corpo = corpo.concat("\nNumero vaccinazioni giornaliere: " + cS.getNumeroVaccinazioniGiornaliere());
				corpo = corpo.concat("\nNumero annullamenti giornalieri: " + cS.getNumeroAnnullamentiGiornalieri());
				corpo = corpo.concat("\n\n");
//				System.out.println(corpo);
			}
			
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		inviaMail(emailImpiegato, corpo);
	}
	
	public void inviaReportStatisticoSettimanale(String emailImpiegato)throws OperationException {
		ArrayList <String> nomiFarmacie = null;
		String corpo = "Report statistico settimanale: \n\n";
		try {
			nomiFarmacie = getNomiFarmacie();
			for(String nomeFarmacia : nomiFarmacie) {
				CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(nomeFarmacia);
				corpo = corpo.concat("Farmacia: " + cS.getNomeFarmacia());
				corpo = corpo.concat("\nNumero prenotazioni settimanali: " + cS.getNumeroPrenotazioniSettimanali());
				corpo = corpo.concat("\nNumero vaccinazioni settimanali: " + cS.getNumeroVaccinazioniSettimanali());
				corpo = corpo.concat("\nNumero annullamenti settimanali: " + cS.getNumeroAnnullamentiSettimanali());
				corpo = corpo.concat("\n\n");
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		
		
		inviaMail(emailImpiegato, corpo);
	}
	
	public void inviaReportStatisticoMensile(String emailImpiegato)throws OperationException {
		ArrayList <String> nomiFarmacie = null;
		String corpo = "Report statistico mensile: \n\n";
		try {
			nomiFarmacie = getNomiFarmacie();
			for(String nomeFarmacia : nomiFarmacie) {
				CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(nomeFarmacia);
				corpo = corpo.concat("Farmacia: " + cS.getNomeFarmacia());
				corpo = corpo.concat("\nNumero prenotazioni Mensili: " + cS.getNumeroPrenotazioniMensili());
				corpo = corpo.concat("\nNumero vaccinazioni Mensili: " + cS.getNumeroVaccinazioniMensili());
				corpo = corpo.concat("\nNumero annullamenti Mensili: " + cS.getNumeroAnnullamentiMensili());
				corpo = corpo.concat("\n\n");
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		
		
		
		inviaMail(emailImpiegato, corpo);
	}
	
	public void inviaElencoTurni(String emailImpiegato) throws OperationException{
		ArrayList<String> usernameFarmacisti = null;
		LocalDate data2 = LocalDate.now();
		LocalDate data1 = data2.minusMonths(1);
		String corpo = 	"Elenco turni per farmacista: \n";
		
		try {
			usernameFarmacisti = getUsernameFarmacisti();
			for (String usernameFarmacista : usernameFarmacisti) {
				try {
					corpo = corpo.concat(usernameFarmacista + ":\n");
					Connection conn = DBManager.getConnection();
					String query = "SELECT * FROM TURNI WHERE FARMACISTA = ? AND DATA BETWEEN ? AND ?";
//					String query = "SELECT * FROM TURNI WHERE FARMACISTA = ?;";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, usernameFarmacista);
					stmt.setString(2, data1.toString());
					stmt.setString(3, data2.toString());
					
					ResultSet r = stmt.executeQuery();
					while(r.next()) {
						corpo = corpo.concat(r.getString(2) + " ");
						if(r.getInt(3) == 0) corpo = corpo.concat("mattina\n");
						else corpo = corpo.concat("pomeriggio-sera\n");
					}
				}catch(SQLException e) {
					throw new DBConnectionException(e.getMessage());
				}
				corpo = corpo.concat("\n");
			}
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		inviaMail(emailImpiegato, corpo);
	}
	
}
