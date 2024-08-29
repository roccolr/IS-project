package boundary;
//import database.*;
//import java.sql.*;

public class Prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCapoFarmacia prova = new BCapoFarmacia();
		prova.registraFarmacista("Luca", "Giordano", "Farmacia Centrale", "lucagiordano@libero.it", false);
		
//		try {
//			Connection conn = DBManager.getConnection();
//			String query = "SELECT NOME FROM FARMACIE WHERE NOME = ?;";
//			try {
//				PreparedStatement p = conn.prepareStatement(query);
//				p.setString(1, "Farmacia Bologna");
//				ResultSet r = p.executeQuery();
//				if(r.next()) {
//					System.out.println(r.getString(1));
//				}
//			}catch(SQLException e) {
//				System.out.println(e.getMessage());
//			}
//		}catch(SQLException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
