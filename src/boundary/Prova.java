package boundary;
//import database.*;
//import java.sql.*;
import control.GestioneSistema;
import exception.OperationException;

import java.time.LocalDate;

public class Prova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BCapoFarmacia prova = new BCapoFarmacia();
//		prova.registraFarmacista("Luca", "Giordano", "Farmacia Centrale", "lucagiordano@libero.it", false);
//		
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
		Dirigente dirigente = new Dirigente();
		BCapoFarmacia cF = new BCapoFarmacia();
		BCliente cliente = new BCliente();
		BFarmacista farmacista = new BFarmacista();
//		cliente.prenotaVaccino("Farmacia Centrale", "2024-09-01", "PFISCHIO", "Maccio", "Capatonda", "macciocapatonda@hotmail.it", "Povere e graminacee");
//		cliente.prenotaVaccino("Farmacia Palermo", "2025-01-01", "ASPERAZENZERO", "Valentino", "Rossi", "valerossidoc@ducati.it", "lentezza");
//		cliente.prenotaVaccino("Farmacia Firenze", "2024-09-01", "ANTIQUA", "Alexander", "Sink", "alesink@coldmail.it", "python");
//		BFarmacista farmacista = new BFarmacista();
		
//		cliente.prenotaVaccino("Farmacia Napoli", "2024-10-11", "PFISCHIO", "Romelu", "Lukaku", "bigrom@adlpappone.it", "juve");
//		cliente.prenotaVaccino("Farmacia Napoli", "2024-10-11", "PFISCHIO", "Kvicha", "Kvaratskhelia", "kvara@adlpappone.it", "juve");
//		
//		dirigente.registraCapoFarmacia("Marco", "Cesaroni", "Farmacia del Corso");
//		dirigente.registraCapoFarmacia("Cesare", "Cesaroni", "Farmacia Firenze");
//		dirigente.registraCapoFarmacia("Rudi", "Cesaroni", "Farmacia Genova");
//		dirigente.registraCapoFarmacia("Mimmo", "Cesaroni", "Farmacia Napoli");
//		dirigente.registraCapoFarmacia("Toto", "Riina", "Farmacia Palermo");
//		dirigente.registraCapoFarmacia("Alexis", "Sanchez", "Farmacia San Marco");
//		dirigente.registraCapoFarmacia("Valentino", "Mazzola", "Farmacia Torino");
//		dirigente.registraCapoFarmacia("Bob", "Marley", "Farmacia Verona");
		
//		cF.registraFarmacista("Milinkovic", "Savic", "Farmacia Centrale", true);
//		cF.registraFarmacista("Ezechiele", "Lavezzi", "Farmacia Centrale", true);
//		cF.registraFarmacista("Silvio", "Berlusconi", "Farmacia Bologna", true);
//		cF.registraFarmacista("Giorgia", "Meloni", "Farmacia Bologna", false);
//		cF.registraFarmacista("a", "b", "Farmacia Bologna", true);
//		cF.registraFarmacista("c", "d", "Farmacia Bologna", true);
		
//		cliente.cancellaAppuntamento("macciocapatonda@hotmail.it", "2024-09-01", "centrale@example.com");
//		cF.cancellaAppuntamento(37);
		
//		cF.inserisciTurni("Farmacia Bologna");
		
//		cliente.prenotaVaccino("Farmacia Bologna", "2024-08-31", "PFISCHIO", "Bruno", "Raspadori", "raspadori@esempio.it", "polvere");
//		farmacista.stampaAppuntamenti("GiorgiaMeloni6486", LocalDate.parse("2024-08-31"));
//		farmacista.inserisciDatiPaziente("Amsterdamite", "alesink@coldmail.it");
//		farmacista.inserisciDatiVaccinazione("2024-08-31","GiorgiaMeloni", "giuliarossi@esempio.it", "Negativo", "Assenza");
		
		GestioneSistema gs = GestioneSistema.getIstance();
		try {
			gs.inviaElencoTurni("patt@live.it");
//			gs.inserisciTurniSettimana("Farmacia Centrale");
			
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}

}
