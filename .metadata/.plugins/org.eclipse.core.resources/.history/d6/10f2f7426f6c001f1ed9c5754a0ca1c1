package control;
import exception.*;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	
//	public void login(BDirigente d,String username, String password) {
////		if(d.username.equals(username) && d.password.equals(password))return true;
////		else return false;
////	
//	}
	
	public  boolean login(String username, String password) throws OperationException {
		ArrayList<String> u=null;
		if(username.contains("CapoFarmacia")) {
			
			try {
				u=CapoFarmaciaDAO.readUsernameCapiFarmacia();
				if(!u.contains(username))throw new  OperationException("Errore: username CapoFarmacia non presente...");
				String p=CapoFarmaciaDAO.readPasswordByUsernameCapiFarmacia(username);
				boolean verifica=password.equals( p);
				if(verifica){
					return verifica;
				}
				else throw new  OperationException("Password CapoFarmacia errata! Riprova");
			}catch(DAOException e) {
				throw new OperationException(e.getMessage());
			}catch(DBConnectionException e) {
				throw new OperationException("Errore durante la connessione al database per la verifica delle credenziali " + e.getMessage());
			}
		}
		else {
			try {
				u=FarmacistaDAO.readUsernameFarmacisti();
				if(!u.contains(username))throw new  OperationException("Errore: username Farmacista non presente...");
				String p=FarmacistaDAO.readPasswordByUsernameFarmacista(username);
				boolean verifica=password.equals(p);
				if(verifica){
					return verifica;
				}
				else throw new  OperationException("Password Farmacista errata! Riprova");
			}catch(DAOException e) {
				throw new OperationException(e.getMessage());
			}catch(DBConnectionException e) {
				throw new OperationException("Errore durante la connessione al database per la verifica delle credenziali " + e.getMessage());
			}
		}
	
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
	
	
	//EFFETTUA L'ANNULLAMENTO DELLA PRENOTAZIONE INVOCANDO IL METODO DEL CAPOFARMACIA DELLA FARMACIA INDICATA
	private static void inviaEmailDisdetta(String emailCliente, String emailFarmacia, int codicePrenotazione) {
		String corpo = "Comunicazione: Il cliente la cui email è " + emailCliente + " desidera disdire la prenotazione con codice " + codicePrenotazione;
		inviaMail(emailFarmacia, corpo);
//		BCapoFarmacia cF ;//= //FarmaciaDAO.readCapoFarmacia(emailFarmacia);
//		cF.cancellaAppuntamento(codicePrenotazione);
	}
	
		
	private static LocalTime findOrario(LocalDate data, String nomeFarmacia) throws OperationException {
		ArrayList<LocalTime> listaOrari;
		try {
			listaOrari = FarmaciaDAO.readOrari(data, nomeFarmacia);
		}catch(DBConnectionException e){
			throw new OperationException("Getting lista orari on DB:" + e.getMessage());
		}catch(DAOException e) {
			throw new OperationException("Getting lista orari: "+e.getMessage());
		}
		if(listaOrari.isEmpty()) return LocalTime.of(8, 0, 0);
		else {
			listaOrari.sort(null);
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
//			orario = listaOrari.get(listaOrari.size()-1).plusMinutes(15);
			
		}

	}
	

	
	
 	public void prenotaVaccino(String nomeFarmacia, LocalDate data, Vaccino vaccino, String nome, String cognome, String email, String allergie)throws OperationException {
		ArrayList<String> nomiFarmacie = null;
		ArrayList<String> emailClienti = null;
		LocalTime orario = null;
		Cliente c = null;
		Farmacia f = null;
		
		try {
			nomiFarmacie = FarmaciaDAO.readNomiFarmacie();

		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia; " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia; " + e.getMessage());
		}
		
		//check sul nomeFarmacia
		if(!(nomiFarmacie.contains(nomeFarmacia))) {
			throw new OperationException("Errore: Farmacia non trovata...");
		}
		
			
		orario = findOrario(data, nomeFarmacia);
		if(orario == null) throw new OperationException("Errore: nessun orario trovato per quel giorno...");

		
		//eventuale salvataggio del cliente
		try {
			emailClienti=ClienteDAO.readEmailClienti();
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
		
//		Prenotazione p = new Prenotazione(data, orario.toString(), nomeFarmacia, email, vaccino);
		Prenotazione p = new Prenotazione(data, orario,nomeFarmacia, email, vaccino);
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
			Vaccinazione vacc = new Vaccinazione(p.getCodice(), p.getNomeFarmacia());
			vacc.save();
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
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		
	}
 	
 	public String getNomeFarmacia(String usernameCapoFarmacia) throws OperationException {
 		String nomeFarmacia=null;
 		try {
			nomeFarmacia=AfferenzaDAO.readFarmaciaFromCapoFarmacia(usernameCapoFarmacia);
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia " + e.getMessage());
		}
		
		return nomeFarmacia;
 		
 	}
	
	public void registraCapoFarmacia(String nome, String cognome, String username, String password, String nomeFarmacia, String emailCapoFarmacia) throws OperationException{
		ArrayList<String> nomiFarmacie = null;
		ArrayList<String> usernameCapiFarmacia = null;
		//si suppone che l'anagrafica del CapoFarmacia sia stata già controllata
		CapoFarmacia cF = new CapoFarmacia(nome, cognome, username, password);
		
		try {
			nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura dei nomi farmacia " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura nomi farmacia " + e.getMessage());
		}
		
		if(!(nomiFarmacie.contains(nomeFarmacia))) throw new OperationException("Errore: La farmacia non e' memorizzata nel sistema");
		
		
		try {
			usernameCapiFarmacia = CapoFarmaciaDAO.readUsernameCapiFarmacia();
			if(usernameCapiFarmacia.contains(cF.getUsername())) throw new OperationException("CapoFarmacia già presente nel sistema");
			else cF.save();
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio del CapoFarmacia "+e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio del CapoFarmacia " + e.getMessage());
		}
		
		try {
			AfferenzaDAO.createAfferenza(nomeFarmacia, cF.getUsername());
		}catch(DAOException e) {
			throw new OperationException("Errore durante il salvataggio dell'afferenza " + e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per il salvataggio dell'afferenza " + e.getMessage());
		}
		
		inviaEmailCredenziali(cF, emailCapoFarmacia);
	}
	
	public void registraFarmacista(String nome, String cognome, String username, String password, String usernameCapoFarmacia, String emailFarmacista, boolean dipendente) throws OperationException{
		ArrayList<String> usernameFarmacisti = null;
		Farmacista f = null;
		String nomeFarmacia;
		try {
			nomeFarmacia=AfferenzaDAO.readFarmaciaFromCapoFarmacia(usernameCapoFarmacia);
//			System.out.println(nomeFarmacia);

		}catch(DAOException e) {
			throw new OperationException("Errore durante la lettura delle afferenze");
		}catch(DBConnectionException e) {
			throw new OperationException("Errore durante la connessione al database per la lettura  delle afferenze");
		}
				
		//si suppone che l'anagrafica del Farmacista sia stata già controllata
//		System.out.println(nomeFarmacia);
		f = new Farmacista(nome, cognome, username, password, dipendente, nomeFarmacia);
		try {
			usernameFarmacisti = FarmacistaDAO.readUsernameFarmacisti();
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
	public void cancellaAppuntamento(String emailCliente, LocalDate data, String emailFarmacia) throws OperationException, DBConnectionException{
		Farmacia f = null;
		ArrayList<String> listaEmailClienti = null;
		int codice;
		
		try {
			f = FarmaciaDAO.readFarmaciaFromEmail(emailFarmacia);
		}catch(DAOException e) {
			throw new OperationException("Errore durante la cancellazione dell'appuntamento: " + e.getMessage());
		}
		
		if (f == null) throw new OperationException("Errore: Nessuna farmacia trovata associata all'email indicata");
		
		//check emailCliente
		try {
			listaEmailClienti = ClienteDAO.readEmailClienti();
			try {
				if(!listaEmailClienti.contains(emailCliente)) throw new OperationException("Errore, l'email utente indicata non è valida...");
			}catch(OperationException e) {
				throw e;
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		
		//ATTENZIONE: Il sistema deve controllare che un cliente non faccia la stessa prenotazione più volte in  
		//farmacie diverse: controlla la funzione prenotaVaccino()
		
		try {
			codice = PrenotazioneDAO.getCodice(data, emailCliente);
//			System.out.println("debug");
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
		
		inviaEmailDisdetta(emailCliente, emailFarmacia, codice);
	}
	
	public void cancellaAppuntamento(int codicePrenotazione) throws OperationException{
		String nomeFarmacia = null;
		try {
			
			Prenotazione p = PrenotazioneDAO.readPrenotazione(codicePrenotazione);
			if(p==null)throw new OperationException("Errore: Nessun codice inserito");
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
			cS.update();
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}
	}
	
//	public ArrayList<String> getUsernameTurniSettimana(String nomeFarmacia) throws OperationException {
//		ArrayList<String> usernameTurni = new  ArrayList<String>();
//		try {	
//			LocalDate today = LocalDate.now();
//			LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
//			LocalDate giorno = nextMonday;
//			ArrayList <Turno> turni=new ArrayList<Turno>();
//			ArrayList <String> uFTurni=new  ArrayList<String>();
//			for(int i=0;i<7;i++) {
//				turni.addAll(TurnoDAO.readTurni(giorno.plusDays(i)));
//			}
//			for (Turno turno :turni) {
//				uFTurni.add(turno.getUsernameFarmacista());
//			}
//			ArrayList <String> nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
//			if(!(nomiFarmacie.contains(nomeFarmacia)))throw new OperationException("Errore inserimento turno: La farmacia non esiste...");
//			ArrayList <String> uF = FarmacistaDAO.readUsernameFarmacisti(nomeFarmacia); 
//			for(String f:uF) {
//				if(uFTurni.contains(f))usernameTurni.add(f);
//			}
//			
//		}catch(DAOException e) {
//			throw new OperationException(e.getMessage());
//		}catch(DBConnectionException e) {
//			throw new OperationException(e.getMessage());
//		}
//		return usernameTurni;
//	}
	
	
	public ArrayList<Turno> getTurniSettimana(String nomeFarmacia) throws OperationException {
		ArrayList<Turno> turni = new  ArrayList<Turno>();
		try {	
			LocalDate today = LocalDate.now();
			LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			LocalDate giorno = nextMonday;
			for(int i=0;i<7;i++) {
				if(TurnoDAO.readTurni(giorno.plusDays(i),nomeFarmacia)!=null)
				turni.addAll(TurnoDAO.readTurni(giorno.plusDays(i),nomeFarmacia));
			}
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		return turni;
	}
	
	
	public void inserisciTurniSettimana(ArrayList <String>usernameFarmacisti,String nomeFarmacia) throws OperationException{
		try {	
			LocalDate today = LocalDate.now();
			LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			LocalDate giorno = nextMonday;
			ArrayList <Turno> turni=new ArrayList<Turno>();
					
			for(int i=0;i<7;i++) {
				turni.addAll(TurnoDAO.readTurni(giorno.plusDays(i),nomeFarmacia));
			}

			ArrayList <String> nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
			if(!(nomiFarmacie.contains(nomeFarmacia)))throw new OperationException("Errore inserimento turno: La farmacia non esiste...");
			
			ArrayList <String> uF = FarmacistaDAO.readUsernameFarmacisti(nomeFarmacia); 
			try {
				Integer i=0;
				for(String user:usernameFarmacisti) {
					if(!(uF.contains(user))) {
						throw new OperationException("Errore: Farmacista"+ i+1 +" non appartenente alla farmacia");
					}
					Turno t = new Turno(i%2, giorno.plusDays(i/2), user);
					t.save();
					i++;
				}
			}catch(NoSuchElementException e) {
				throw new OperationException("Errore con l'input da tastiera...");
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
	
	public void stampaAppuntamenti(String usernameFarmacista, LocalDate data)throws OperationException, IOException {
		ArrayList<Turno> turni = null;
		ArrayList<String> uF;
		try {
			uF = FarmacistaDAO.readUsernameFarmacisti();
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		boolean verificaUsername=uF.contains(usernameFarmacista);
		if(!verificaUsername) throw new OperationException("Error: Username inserito non presente");
		String nFarmacia;
		try {
			nFarmacia = FarmacistaDAO.readFarmacia(usernameFarmacista);
		} catch (DAOException e) {
			throw new OperationException(e.getMessage());
		} catch (DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
		try {
			turni = TurnoDAO.readTurni(data,nFarmacia);
			
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
						else if(tipo == 1){
							stmt.setString(2, orarioPrimoAppuntamentoSera);
							stmt.setString(3, orarioUltimoAppuntamentoSera);
						}
						else throw new OperationException("Il Farmacista inserito non ha turni nella data "+data.toString());
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
							daStampare.put(new Prenotazione(d,o,nomeFarmacia,emailCliente,Vaccino.valueOf(nomeVaccino)), temp);
//							System.out.println("debug");
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
		}
		
		if(daStampare.isEmpty()) throw new OperationException("Non c'è nessun appuntamento per il giorno selezionato...");
		else {
			DataOutputStream fout= new DataOutputStream(new FileOutputStream("Appuntamenti_"+data.toString()+".txt"));
			try {
				
				for(Map.Entry<Prenotazione, ArrayList<String>> entry : daStampare.entrySet()) {
					String giorno = entry.getKey().getData().toString();
					String orario = entry.getKey().getOrario().toString();
					String vaccino = entry.getKey().getNomeVaccino();
					String nomeCliente = entry.getValue().get(0);
					String cognomeCliente = entry.getValue().get(1);
					
					try {
						fout.writeUTF("Prenotazione per il " + giorno + " alle " + orario + "; effettuare vaccino " + vaccino + " al cliente " + nomeCliente + " " + cognomeCliente+"\n");
						System.out.println("Prenotazione per il " + giorno + " alle " + orario + "; effettuare vaccino " + vaccino + " al cliente " + nomeCliente + " " + cognomeCliente+"\n");
					} catch (IOException e) {
						throw new IOException(e.getMessage());
					}
				}
			}catch (FileNotFoundException e) {
					throw new FileNotFoundException(e.getMessage());
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}finally {
				fout.close();
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
		if(c==null)throw new OperationException("Error: E-mail non associata a nessun cliente...");
		c.setDatiAnamnestici(datiAnamnestici);
		try {
			ClienteDAO.updateCliente(c, emailCliente);
		}catch(DAOException e) {
			throw new OperationException(e.getMessage());
		}catch(DBConnectionException e) {
			throw new OperationException(e.getMessage());
		}
	}
	
	//modifica: Motivazione viene inserito solo se l'esito risulta negativo
	public void inserisciDatiVaccinazione(LocalDate data,String usernameFarmacista ,String emailCliente, String esito, String motivazione) throws OperationException{
		int codicePrenotazione, codiceVaccinazione;
		Prenotazione p = null;
		Vaccinazione v = null;
		String esitoEsistente= null;
		//Il check su esito/motivazione viene fatto a monte
		try {
//			 = VaccinazioneDAO.getEsitoVaccinazione(codicePrenotazione);

			codicePrenotazione = PrenotazioneDAO.getCodice(data, emailCliente);
			esitoEsistente= VaccinazioneDAO.readEsitoVaccinazione(codicePrenotazione);
			if(esitoEsistente==null)throw new OperationException("Errore: Dati vaccinazione gia inseriti...");
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
		
		if(esito.equalsIgnoreCase("positivo")) {
			try {
				CartellaStatistica cS = CartellaStatisticaDAO.readCartellaStatistica(p.getNomeFarmacia());
				cS.incrementaVaccinazioni();
				cS.update();
			}catch(DAOException e) {
				throw new OperationException(e.getMessage());
			}catch(DBConnectionException e) {
				throw new OperationException(e.getMessage());
			}
		}
		else {
			v.setMotivazione(motivazione);
		}
	}
	
	//public void motivaVaccinazioneNonEffettuata()throws DAOException, DBConnectionException,OperationException {}
	//commentata perchè viene gestito in inserisciDAtiVaccinazione
	
	
	public void inviaReportStatisticoGiornaliero(String emailImpiegato)throws OperationException {
		ArrayList <String> nomiFarmacie = null;
		String corpo = "Report statistico giornaliero: \n\n";
		try {
			nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
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
			nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
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
			nomiFarmacie = FarmaciaDAO.readNomiFarmacie();
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
			usernameFarmacisti = FarmacistaDAO.readUsernameFarmacisti();
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
