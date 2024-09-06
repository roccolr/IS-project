package database;

import java.sql.*;

import entity.CartellaStatistica;
import exception.DAOException;
import exception.DBConnectionException;

public class CartellaStatisticaDAO {
	
	public static void createCartellaStatistica (CartellaStatistica cS) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO CARTELLESTATISTICHE VALUES (?,?,?,?,?,?,?,?,?,?);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, cS.getNomeFarmacia());
				stmt.setInt(2, cS.getNumeroPrenotazioniGiornaliere());
				stmt.setInt(3, cS.getNumeroPrenotazioniSettimanali());
				stmt.setInt(4, cS.getNumeroPrenotazioniMensili());
				stmt.setInt(5, cS.getNumeroVaccinazioniGiornaliere());
				stmt.setInt(6, cS.getNumeroVaccinazioniSettimanali());
				stmt.setInt(7, cS.getNumeroVaccinazioniMensili());
				stmt.setInt(8, cS.getNumeroAnnullamentiGiornalieri());
				stmt.setInt(9, cS.getNumeroAnnullamentiSettimanali());
				stmt.setInt(10, cS.getNumeroAnnullamentiMensili());

				stmt.executeUpdate();

			}catch(SQLException e) {
				throw new DAOException("Errore scrittura CartellaStatistica");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
	}
	
	public static CartellaStatistica readCartellaStatistica (String nomeFarmacia) throws DAOException, DBConnectionException{
		CartellaStatistica c = null;
		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CARTELLESTATISTICHE WHERE FARMACIA = ?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				
				stmt.setString(1, nomeFarmacia);

				ResultSet r = stmt.executeQuery();
				if(r.next()) {
					c = new CartellaStatistica(r.getString(1), r.getInt(2), r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6), r.getInt(7), r.getInt(8), r.getInt(9), r.getInt(10));
				}

			}catch(SQLException e) {
				throw new DAOException("Errore lettura CartellaStatistica");
			} finally {
				DBManager.closeConnection();
			}
		
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}
		return c;
	}
	
	public static void updateCartellaStatistica(CartellaStatistica cS, String oldNomeFarmacia) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "UPDATE CARTELLESTATISTICHE SET "
					   + "FARMACIA = ?, "
			           + "NUMEROPRENOTAZIONIGIORNALIERE = ?, "
			           + "NUMEROPRENOTAZIONISETTIMANALI = ?, "
			           + "NUMEROPRENOTAZIONIMENSILI = ?, "
			           + "NUMEROVACCINAZIONIGIORNALIERE = ?, "
			           + "NUMEROVACCINAZIONISETTIMANALI = ?, "
			           + "NUMEROVACCINAZIONIMENSILI = ?, "
			           + "NUMEROANNULLAMENTIGIORNALIERI = ?, "
			           + "NUMEROANNULLAMENTISETTIMANALI = ?, "
			           + "NUMEROANNULLAMENTIMENSILI = ? "
			           + "WHERE FARMACIA = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, cS.getNomeFarmacia());
				stmt.setInt(2, cS.getNumeroPrenotazioniGiornaliere());
				stmt.setInt(3, cS.getNumeroPrenotazioniSettimanali());
				stmt.setInt(4, cS.getNumeroPrenotazioniMensili());
				stmt.setInt(5, cS.getNumeroVaccinazioniGiornaliere());
				stmt.setInt(6, cS.getNumeroVaccinazioniSettimanali());
				stmt.setInt(7, cS.getNumeroVaccinazioniMensili());
				stmt.setInt(8, cS.getNumeroAnnullamentiGiornalieri());
				stmt.setInt(9, cS.getNumeroAnnullamentiSettimanali());
				stmt.setInt(10, cS.getNumeroAnnullamentiMensili());	
				stmt.setString(11, oldNomeFarmacia);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore aggiornamento CartellaStatistica...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
	
	public static void deleteCartellaStatistica(String nomeFarmacia) throws DAOException, DBConnectionException{
		try {
			Connection conn = DBManager.getConnection();
			String query = "DELETE FROM CARTELLESTATISTICHE WHERE FARMACIA = ?;";
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, nomeFarmacia);
				stmt.executeUpdate();
			}catch(SQLException e) {
				throw new DAOException("Errore cancellazione CartellaStatistica...");
			}
			finally {
				DBManager.closeConnection();
			}
		}catch(SQLException e) {
			throw new DBConnectionException("Errore connessione database...");
		}
	}
}
