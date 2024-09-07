package database;
import entity.Farmacia;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class FarmaciaDAO {
	public static void createFarmacia(Farmacia f) throws DAOException, DBConnectionException {
		String address = f.getIndirizzo();
        String[] parts = address.split(" ");

        String via = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 3));
        String civico = parts[parts.length - 3];
        String cap = parts[parts.length - 2];
        String citta = parts[parts.length - 1];
        
		try {
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO FARMACIE VALUES (?,?,?,?,?,?);";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, f.getNome());
				stmt.setString(2, f.getEmail());
				stmt.setString(3, via);
				stmt.setString(4, civico);
				stmt.setString(5, cap);
				stmt.setString(6, citta);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore creazione Farmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
//	public static ArrayList<String> readUsernameFarmacistiFromFarmacia(Farmacia f) throws DAOException, DBConnectionException {
//		 ArrayList<String>unsernameFarmacisti=new ArrayList<String>();
//       
//		try {
//			Connection conn = DBManager.getConnection();
//			String query = "SELECT USERNAME FROM FARMACISTI F JOIN FARMACIE ON F.FARMACIA=? ";
//			try {
//				PreparedStatement stmt = conn.prepareStatement(query);
//				stmt.setString(1, f.getNome());
//
//				
//				stmt.executeUpdate();
//			}catch(SQLException e) {
//				throw new DAOException("Errore creazione Farmacia...");
//			}finally {
//				DBManager.closeConnection();
//			}
//		}catch(SQLException e) {
//			throw new DBConnectionException("Errore connessione database...");
//		}
//		return unsernameFarmacisti;
//	}
	
	public static ArrayList<String> readNomiFarmacie() throws DAOException, DBConnectionException{
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
	
	
	public static Farmacia readFarmaciaFromNome(String nome) throws DAOException, DBConnectionException{
		Farmacia f = null;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM FARMACIE WHERE NOME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nome);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					String via = r.getString(3);
					String civico = r.getString(4);
					String cap = r.getString(5);
					String citta = r.getString(6);
					String indirizzo = String.join(" ", via, civico, cap, citta);
					f = new Farmacia(r.getString(1), indirizzo, r.getString(2));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return f;
	}
	
	public static Farmacia readFarmaciaFromEmail(String email)throws DAOException, DBConnectionException{
		Farmacia f = null;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM FARMACIE WHERE EMAIL = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, email);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					String via = r.getString(3);
					String civico = r.getString(4);
					String cap = r.getString(5);
					String citta = r.getString(6);
					String indirizzo = String.join(" ", via, civico, cap, citta);
					f = new Farmacia(r.getString(1), indirizzo, r.getString(2));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return f;
	}
	
	public static void updateFarmacia(Farmacia f, String oldNome) throws DAOException, DBConnectionException {
		String address = f.getIndirizzo();
        String[] parts = address.split(" ");

        String via = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 3));
        String civico = parts[parts.length - 3];
        String cap = parts[parts.length - 2];
        String citta = parts[parts.length - 1];
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE FARMACIE SET NOME = ?, EMAIL = ?, VIA = ?, CIVICO = ?, CAP = ?, CITTA = ? WHERE NOME = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, f.getNome());
				stmt.setString(2, f.getEmail());
				stmt.setString(3, via);
				stmt.setString(4, civico);
				stmt.setString(5, cap);
				stmt.setString(6, citta);
				stmt.setString(7, oldNome);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento Farmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static ArrayList<LocalTime> readOrari(LocalDate giorno, String nomeFarmacia)throws DAOException, DBConnectionException,OperationException{
		ArrayList<LocalTime> listaOrari = new ArrayList<>();
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT ORARIO FROM PRENOTAZIONI P JOIN VACCINAZIONI V ON P.CODICE = V.CODICEPRENOTAZIONE WHERE NOMEFARMACIA = ? AND DATA=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeFarmacia);
				stmt.setString(2, giorno.toString());
		    	System.out.println("Giorno richiesto per la prenotazione: "+giorno.toString());
				ResultSet r = stmt.executeQuery();

				while(r.next()) {
					listaOrari.add(r.getTime(1).toLocalTime());
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
	
	public static void deleteFarmacia(String nome) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM FARMACIA WHERE NOME = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nome);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Farmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
}
