package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura Farmacista");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
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
					f = new Farmacista(r.getString(2), r.getString(3), r.getString(1), r.getString(4), r.getBoolean(5), r.getString(6));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacista...");
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
