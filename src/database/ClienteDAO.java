package database;
import entity.Cliente;
import java.sql.*;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteDAO {
	public static void createCliente(Cliente c) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "INSERT INTO CLIENTI VALUES (?, ?, ?, ?, ?);";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, c.getEmail());
				stmt.setString(2, c.getNome());
				stmt.setString(3, c.getCognome());
				stmt.setString(4, c.getAllergie());
				stmt.setNull(1, Types.VARCHAR);
				
				stmt.executeUpdate();
			}catch (SQLException e){
				throw new DAOException("Errore creazione nuovo Cliente...");
			}finally {
				DBManager.closeConnection();
			}
		}catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static Cliente readCliente(String email) throws DAOException, DBConnectionException{
		Cliente c = null;
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM CLIENTI WHERE USERNAME = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, email);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					c = new Cliente(r.getString(2), r.getString(3), r.getString(1), r.getString(4));
				}
			}catch (SQLException e){
				throw new DAOException("Errore lettura Cliente...");
			}finally {
				DBManager.closeConnection();
			}
		}catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return c;
	}
	
	public static void updateCliente(Cliente c, String oldEmail) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE CLIENTI SET EMAIL = ?, NOME = ?, COGNOME = ?, ALLERGIE = ?, DATIANAMNESTICI = ? WHERE EMAIL = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, c.getEmail());
				stmt.setString(2, c.getNome());
				stmt.setString(3, c.getCognome());
				stmt.setString(4, c.getAllergie());
				stmt.setString(5, c.getDatiAnamnestici());
				stmt.setString(6, oldEmail);
				
				stmt.executeUpdate();
			}catch (SQLException e){
				throw new DAOException("Errore aggiornamento Cliente...");
			}finally {
				DBManager.closeConnection();
			}
		}catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteCliente(String email) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM CLIENTI WHERE USERNAME = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, email);
				
				stmt.executeUpdate();
			}catch (SQLException e){
				throw new DAOException("Errore cancellazione Cliente...");
			}finally {
				DBManager.closeConnection();
			}
		}catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
