package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Farmacista;
import exception.DAOException;
import exception.DBConnectionException;

public class FarmacistaDAO {
	public static void createFarmacista (Farmacista f) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO FARMACISTI VALUES (?,?,?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, (f.getUsername()));
				stmt.setString(2, f.getNome());
				stmt.setString(3, f.getCognome());
				stmt.setString(4, f.getPassword());
				stmt.setBoolean(5, f.isDipendente());
				stmt.setString(6, f.getNomeFarmacia());
				
//				System.out.println(f.getNome() + " " + f.getCognome() + f.getUsername() + f.getPassword() + f.isDipendente());
				
				stmt.executeUpdate();
//				System.out.println("debug");

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura Farmacista" + e.getMessage() + " " + e.getErrorCode());
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	public static ArrayList<String> readUsernameFarmacisti()throws DAOException, DBConnectionException{
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
//					System.out.println(r.getString(1));
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
	
	public static ArrayList<String> readUsernameFarmacisti(String nomeFarmacia)throws DAOException, DBConnectionException{
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
	
	public static String readPasswordByUsernameFarmacista(String username)throws DAOException, DBConnectionException{
		String passwordFarmacista = "pollo"; 
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT PASSWORD FROM FARMACISTI WHERE USERNAME=?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, username);

				ResultSet r = stmt.executeQuery();
				if(r.next()) {
//					System.out.println(r.getString(1));				
					passwordFarmacista=r.getString(1);
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacista: username non presente...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return passwordFarmacista;
	}
	
	public static String readFarmacia (String username) throws DAOException, DBConnectionException{
		String  nomeFarmacia = "";
		try {
			Connection conn = DBManager.getConnection();
			
			String query = "SELECT FARMACIA FROM FARMACISTI WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, username);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					nomeFarmacia=r.getString(1);
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacista... " + e.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return nomeFarmacia;
	}
	
	public static Farmacista readFarmacista (String username) throws DAOException, DBConnectionException{
		Farmacista f = null;
		try {
			Connection conn = DBManager.getConnection();
			
			String query = "SELECT * FROM FARMACISTI WHERE USERNAME = ?;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, username);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					f = new Farmacista(r.getString(2), r.getString(3), r.getString(1), r.getString(4), r.getBoolean(5),r.getString(6));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacista... " + e.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return f;
	}
	
	public static void updateFarmacista(Farmacista f, String oldUsername) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE FARMACISTI SET USERNAME = ?, NOME = ?, COGNOME = ?, PASSWORD = ?, DIPENDENTE = ?, FARMACIA = ? WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, f.getUsername());
				stmt.setString(2, f.getNome());
				stmt.setString(3, f.getCognome());
				stmt.setString(4, f.getPassword());
				stmt.setBoolean(5, f.isDipendente());
				stmt.setString(6, "");
				stmt.setString(7, oldUsername);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento Farmacista...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteFarmacista(String email) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM FARMACISTI WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, email);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Farmacista...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
