package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.DAOException;
import exception.DBConnectionException;

public class AfferenzaDAO {
	public static void createAfferenza(String nomeFarmacia, String usernameCapoFarmacia) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO AFFERENZE VALUES (?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, nomeFarmacia);
				stmt.setString(2, usernameCapoFarmacia);
				System.out.println(nomeFarmacia+ " " + usernameCapoFarmacia );

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura Afferenza..."+e.getMessage());
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	public static String readFarmaciaFromCapoFarmacia(String usernameCapoFarmacia)throws DAOException, DBConnectionException{
		String farmacia = null;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT NOMEFARMACIA FROM AFFERENZE WHERE USERNAME_CF = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, usernameCapoFarmacia);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					farmacia = r.getString(1);
//					System.out.println(farmacia);
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Afferenza...");
			} finally {
				DBManager.closeConnection();
				
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		System.out.println(farmacia);

		return farmacia;
	}
	
	public static String readCapoFarmaciaFromFarmacia(String nomeFarmacia)throws DAOException, DBConnectionException{
		String capoFarmacia = null;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT USERNAME_CF FROM AFFERENZE WHERE NOMEFARMACIA = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, nomeFarmacia);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					capoFarmacia = r.getString(1);
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Afferenza...");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return capoFarmacia;
	}
	
	public static void updateAfferenza(String nomeFarmacia, String usernameCapoFarmacia, String oldNomeFarmacia) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE AFFERENZE SET NOMEFARMACIA = ?, USERNAME_CF = ? WHERE NOMEFARMACIA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, nomeFarmacia);
				stmt.setString(2, usernameCapoFarmacia);
				stmt.setString(3, oldNomeFarmacia);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento Afferenza...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteAfferenza(String nomeFarmacia)throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM AFFERENZE WHERE NOMEFARMACIA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeFarmacia);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Afferenza...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
