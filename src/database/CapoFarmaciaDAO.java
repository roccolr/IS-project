package database;
import java.sql.*;
import entity.CapoFarmacia;
import exception.DAOException;
import exception.DBConnectionException;

public class CapoFarmaciaDAO {
	public static void createCapoFarmacia (CapoFarmacia cf) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO CAPIFARMACIA VALUES (?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, (cf.getUsername()));
				stmt.setString(2, cf.getNome());
				stmt.setString(3, cf.getCognome());
				stmt.setString(4, cf.getPassword());

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura CapoFarmacia" + e.getMessage());
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	public static CapoFarmacia readCapoFarmacia (String username) throws DAOException, DBConnectionException{
		CapoFarmacia eCF = null;
		try {
			Connection conn = DBManager.getConnection();
			
			String query = "SELECT * FROM CAPIFARMACIA WHERE USERNAME = ?;";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, username);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					eCF = new CapoFarmacia(r.getString(2), r.getString(3), r.getString(1), r.getString(4));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura CapoFarmacia...");
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return eCF;
	}
	
	public static void updateCapoFarmacia(CapoFarmacia cf, String oldUsername) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE CAPIFARMACIA SET USERNAME = ?, NOME = ?, COGNOME = ?, PASSWORD = ? WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, cf.getUsername());
				stmt.setString(2, cf.getNome());
				stmt.setString(3, cf.getCognome());
				stmt.setString(4, cf.getPassword());
				stmt.setString(5, oldUsername);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento CapoFarmacia...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteCapoFarmacia(String email) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM CAPIFARMACIA WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, email);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione CapoFarmacia...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
