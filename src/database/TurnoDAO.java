package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.Turno;
import exception.DAOException;
import exception.DBConnectionException;

public class TurnoDAO {
	

	
	public static void createTurno (Turno t) throws DAOException, DBConnectionException{
		String data = t.getData().toString();
		try {
			Connection conn = DBManager.getConnection();
			
			String query = "INSERT INTO TURNI (DATA, TIPO, FARMACISTA) VALUES (?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, data);
				stmt.setInt(2, t.getTipo());
				stmt.setString(3, t.getUsernameFarmacista());

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura Turno");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	public static ArrayList<Turno> readTurni(LocalDate data,String nomeFarmacia)throws DAOException, DBConnectionException{
		ArrayList <Turno> turni = new ArrayList<>(); //lista da ritornare
		String d = data.toString();
		try {
			Connection conn = DBManager.getConnection();
			String query = "SELECT * FROM TURNI T JOIN FARMACISTI F ON F.USERNAME=T.FARMACISTA WHERE T.DATA = ? and F.FARMACIA=?";
//			String query = "SELECT * FROM TURNI T JOIN FARMACISTI F ON F.USERNAME=T.FARMACISTA WHERE DATA = ? , F.FARMACIA=?;";
//			String query =" SELECT * FROM TURNI T JOIN FARMACISTI F ON F.USERNAME=T.FARMACISTA WHERE T.DATA = "2024-09-09" and F.FARMACIA="Farmacia Bologna";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, d);
				stmt.setString(2, nomeFarmacia);
				ResultSet r = stmt.executeQuery();
				while(r.next()) {
					turni.add(new Turno(r.getInt(3), LocalDate.parse(r.getString(2)), r.getString(4),r.getInt(1)));
//					System.out.println(r.getInt(3));
				}
			}catch(SQLException e) {
				throw new DAOException("Errore lettura Farmacisti..."+e.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
		return turni;
	}
		
	public static int getCodice(String data, String usernameFarmacista) throws DAOException, DBConnectionException{
		int codice = -1;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT CODICE FROM TURNI WHERE DATA = ?, FARMACISTA = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, data);
				stmt.setString(2, usernameFarmacista);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					codice = r.getInt(1);
				}
				else {
					throw new DAOException("Errore: Nessun Turno trovato...");
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Turno...");
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
		
		
	public static String readUsernameFarmacista(int codice) throws DAOException, DBConnectionException{
		String usernameFarmacista = null;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT FARMACISTA FROM TURNI WHERE CODICE = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, codice);
				
				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					usernameFarmacista = r.getString(1);
				}
				else {
					throw new DAOException("Errore: Nessun Turno trovato...");
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura Turno...");
			}catch(DAOException ee) {
				System.out.println(ee.getMessage());
			}finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return usernameFarmacista;
	}
	
	
	public static void updateTurno(Turno t, int oldCodice)throws DAOException, DBConnectionException {
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE TURNI SET DATA = ?, TIPO = ?, FARMACISTA = ? WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1,t.getData().toString());
				stmt.setInt(2, t.getTipo());
				stmt.setString(3, t.getUsernameFarmacista());
				stmt.setInt(4, oldCodice);
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento Turno...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteTurno(int codice)throws DAOException, DBConnectionException {
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM TURNI WHERE CODICE = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, codice);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione Turno...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
