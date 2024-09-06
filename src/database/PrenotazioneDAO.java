package database;
import java.sql.*;
import entity.Vaccino;
//import entity.CapoFarmacia;
import entity.Prenotazione;
//import entity.Farmacia;
import exception.DAOException;
import exception.DBConnectionException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class PrenotazioneDAO {
	
	public static int getCodice(Prenotazione p) throws DAOException, DBConnectionException{
		int codice = -1;
		String d = p.getData().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String o = p.getOrario().format(formatter);
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT CODICE FROM PRENOTAZIONI WHERE DATA = ? AND ORARIO = ? AND EMAILCLIENTE = ?;";
//			System.out.println(d + " " + o + " " + p.getEmailCliente());
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, d);
				stmt.setString(2, o);
				stmt.setString(3, p.getEmailCliente());
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codice = r.getInt(1);
				}
				else {
					throw new DAOException("Errore: Nessuna Prenotazione trovata...");
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Prenotazione...");
			}catch(DAOException ee) {
				System.out.println(ee.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return codice;
	}
	
	public static int getCodice(LocalDate giorno, String emailCliente) throws DAOException, DBConnectionException{
		int codice = -1;
		String d = giorno.toString();
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT CODICE FROM PRENOTAZIONI WHERE DATA = ? AND EMAILCLIENTE = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, d);
				stmt.setString(2, emailCliente);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codice = r.getInt(1);
				}
				else {
					throw new DAOException("Errore: Nessuna Prenotazione trovata...");
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Prenotazione...");
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return codice;
	}
	
	public static void createPrenotazione(Prenotazione p) throws DAOException, DBConnectionException{
		String data = p.getData().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String orario = p.getOrario().format(formatter);
//		System.out.println(data + " " + orario + " " + p.getNomeVaccino() + " " + p.getEmailCliente());
		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO PRENOTAZIONI (DATA, ORARIO, VACCINO ,EMAILCLIENTE) VALUES (?, ?, ?, ?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, data);
				stmt.setString(2, orario);
				stmt.setString(3, p.getNomeVaccino());
				stmt.setString(4, p.getEmailCliente());
				stmt.executeUpdate();
//				System.out.println("debug");

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura Prenotazione");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
//	public static Prenotazione readPrenotazione(LocalDate data, LocalTime orario, Farmacia farmacia, Cliente cliente) throws DAOException, DBConnectionException{
//		int codice = getCodice(data, orario);
//		Prenotazione p = null;
//		try {
//			Connection conn = DBManager.getConnection();
//			
//			String query = "SELECT P.DATA, P.ORARIO, P.VACCINO, P.EMAILCLIENTE, V.NOMEFARMACIA\n"
//					+ "FROM VACCINAZIONI V JOIN PRENOTAZIONI P ON V.CODICEPRENOTAZIONE = P.CODICE\n"
//					+ "WHERE P.CODICE = ?;";
//			
//			try {
//				PreparedStatement stmt = conn.prepareStatement(query);
//				stmt.setInt(1, codice);
//				
//				ResultSet r = stmt.executeQuery();
//				if(r.next()) {
//					LocalDate d = LocalDate.parse(r.getString(1));
//					LocalTime o = LocalTime.parse(r.getString(2));
//					p = new Prenotazione(d, o)
//				}
//			}catch(SQLException e) {
//				throw new DAOException("Errore lettura CapoFarmacia...");
//			}finally {
//				DBManager.closeConnection();
//			}
//		}catch(SQLException e) {
//			throw new DBConnectionException("Errore connessione database...");
//		}
//		return p;
//		
//	}
	
	public static Prenotazione readPrenotazione(int codice)throws DAOException, DBConnectionException{
		Prenotazione p = null;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT P.CODICE, P.DATA, P.ORARIO, P.VACCINO, V.NOMEFARMACIA, P.EMAILCLIENTE FROM PRENOTAZIONI P JOIN VACCINAZIONI V ON P.CODICE = V.CODICEPRENOTAZIONE WHERE P.CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, codice);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					LocalDate data = LocalDate.parse(r.getString(2));
					LocalTime orario = LocalTime.parse(r.getString(3));
					p = new Prenotazione(data, orario, r.getString(5), r.getString(6) , Vaccino.valueOf(r.getString(4)));
				}
				else {
					throw new DAOException("Errore: Nessuna Prenotazione trovata...");
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Prenotazione...");
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return p;
	}
	
	public static void deletePrenotazione(int codice) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM PRENOTAZIONI WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codice);
				stmt.executeUpdate();
//				System.out.println("debug");
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Prenotazione..." + e.getMessage());
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static int getConteggioPrenotazioni(Prenotazione p) throws DAOException, DBConnectionException{
		int res = 0;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT COUNT(*) FROM PRENOTAZIONI WHERE DATA= ? AND EMAILCLIENTE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, p.getData().toString());
				stmt.setString(2, p.getEmailCliente());
				ResultSet r = stmt.executeQuery();
				
				if(r.next()) {
					res = r.getInt(1);
				}
//				System.out.println(res);
			}catch(SQLException e) {
				throw new DAOException("Errore query count(*) Prenotazione...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return res;
	}
}
