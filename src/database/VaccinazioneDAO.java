package database;
import java.sql.*;

import entity.Vaccinazione;
import exception.DAOException;
import exception.DBConnectionException;


public class VaccinazioneDAO {
	public static int getCodice(Vaccinazione v) throws DAOException, DBConnectionException{
		int codice = -1;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT CODICE FROM VACCINAZIONI WHERE CODICEPRENOTAZIONE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, v.getPrenotazione().getCodice());
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codice = r.getInt(1);
				}
				else throw new DAOException("Errore: Nessuna riscontro trovato nel DB...");
			}catch (SQLException e) {
				throw new DAOException("Errore query Vaccinazione...");
			}catch(DAOException d) {
				System.out.println(d.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return codice;
	}
	
	public static int getCodice(int codicePrenotazione) throws DAOException, DBConnectionException{
		int codice = -1;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT CODICE FROM VACCINAZIONI WHERE CODICEPRENOTAZIONE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codicePrenotazione);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codice = r.getInt(1);
				}
				else throw new DAOException("Errore: Nessuna riscontro trovato nel DB...");
			}catch (SQLException e) {
				throw new DAOException("Errore query Vaccinazione...");
			}catch(DAOException d) {
				System.out.println(d.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return codice;
	}
	
	public static String getUsernameFarmacista(Vaccinazione v) throws DAOException, DBConnectionException{
		String usernameFarmacista = null;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT USERNAMEFARMACISTA FROM VACCINAZIONI WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, v.getCodice());
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					usernameFarmacista = r.getString(1);
				}
				else throw new DAOException("Errore: Nessuna riscontro trovato nel DB...");
			}catch (SQLException e) {
				throw new DAOException("Errore query Vaccinazione...");
			}catch(DAOException d) {
				System.out.println(d.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return usernameFarmacista;
	}
	
	public static String getNomeFarmacia(Vaccinazione v) throws DAOException, DBConnectionException{
		String nomeFarmacia = null;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT NOMEFARMACIA FROM VACCINAZIONI WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, v.getCodice());
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					nomeFarmacia = r.getString(1);
				}
				else throw new DAOException("Errore: Nessun riscontro trovato nel DB...");
			}catch (SQLException e) {
				throw new DAOException("Errore query Vaccinazione...");
			}catch(DAOException d) {
				System.out.println(d.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return nomeFarmacia;
	}
	
	public static int getCodicePrenotazione(Vaccinazione v) throws DAOException, DBConnectionException{
		int codicePrenotazione = -1;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT P.CODICE FROM VACCINAZIONI V JOIN PRENOTAZIONI P ON V.CODICEPRENOTAZIONE = P.CODICE WHERE P.CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, v.getCodice());
//				System.out.println("debug");
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codicePrenotazione = r.getInt(1);
				}
				else throw new DAOException("Errore: Nessun riscontro trovato nel DB...");
			}catch (SQLException e) {
				throw new DAOException("Errore  query Vaccinazione..." + " " + e.getMessage());
			}catch(DAOException d) {
				System.out.println(d.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return codicePrenotazione;
	}
	
//	public static Vaccinazione readVaccinazione(int codice) throws DAOException, DBConnectionException{
//		Vaccinazione v = null;
//		try {
//			Connection conn = DBManager.getConnection();
//			
//			String query = "SELECT * FROM VACCINAZIONI WHERE CODICE = ?;";
//			
//			try {
//				PreparedStatement stmt = conn.prepareStatement(query);
//				stmt.setString(1, codice);
//				
//				ResultSet r = stmt.executeQuery();
//				if(r.next()) {
//					v = new Vaccinazione()
//				}
//			}catch(SQLException e) {
//				throw new DAOException("Errore lettura CapoFarmacia...");
//			}finally {
//				DBManager.closeConnection();
//			}
//		}catch(SQLException e) {
//			throw new DBConnectionException("Errore connessione database...");
//		}
//		return v;
//	}
	
	
	public static void createVaccinazione(int codicePrenotazione, String nomeFarmacia) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO VACCINAZIONI (CODICEPRENOTAZIONE, NOMEFARMACIA) VALUES (?, ?);";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codicePrenotazione);
				stmt.setString(2, nomeFarmacia);
				
//				System.out.println(codicePrenotazione + " " + nomeFarmacia);
				stmt.executeUpdate();
			}catch (SQLException e) {
				throw new DAOException("Errore creazione Vaccinazione...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static String readEsitoVaccinazione(int codicePrenotazione) throws DAOException, DBConnectionException{
		String esito=null;

		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT ESITO FROM VACCINAZIONI WHERE CODICEPRENOTAZIONE=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codicePrenotazione);
				ResultSet r = stmt.executeQuery();

//				System.out.println(codicePrenotazione + " " + nomeFarmacia);
				if(r.next()) {
					esito = r.getString(1);
				}
			}catch (SQLException e) {
				throw new DAOException("Errore lettura esito Vaccinazione..."+e.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return esito;
	}
	
	public static void updateVaccinazione(Vaccinazione v, int oldCodice)throws DAOException, DBConnectionException {
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE VACCINAZIONI SET ESITO = ?, MOTIVAZIONE = ?, CODICEPRENOTAZIONE = ?, USERNAMEFARMACISTA = ?, NOMEFARMACIA = ? WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, v.getEsito());
				stmt.setString(2, v.getMotivazione());
				stmt.setInt(3, v.getCodicePrenotazione());
				stmt.setString(4, v.getUsernameFarmacista());
				stmt.setString(5, v.getNomeFarmacia());
				stmt.setInt(6, oldCodice);
//				System.out.println("debug");
				

				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento Vaccinazione...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteVaccinazione(int codice) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM VACCINAZIONI WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codice);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Vaccinazione...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
