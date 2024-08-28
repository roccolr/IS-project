package database;
import java.sql.*;

//import entity.CapoFarmacia;
import entity.Prenotazione;
//import entity.Farmacia;
import entity.Cliente;
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

			String query = "SELECT CODICE FROM PRENOTAZIONI WHERE DATA = ?, ORARIO = ?, EMAILCLIENTE = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, d);
				stmt.setString(2, o);
				stmt.setString(3, p.getCliente().getEmail());
				
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
	
	public static void createPrenotazione(Prenotazione p) throws DAOException, DBConnectionException{
		String data = p.getData().toString();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String orario = p.getOrario().format(formatter);
		
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
	
}
