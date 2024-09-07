package boundary;
import control.GestioneSistema;
import exception.OperationException;

public class tempo {
	public void inviaReportGiornalieri(String emaildirigente) {
		try{
			GestioneSistema gs = GestioneSistema.getIstance();
			gs.inviaReportStatisticoGiornaliero(emaildirigente);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inviaReportSettimanali(String emaildirigente) {
		try{
			GestioneSistema gs = GestioneSistema.getIstance();
			gs.inviaReportStatisticoSettimanale(emaildirigente);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	public void inviaReportMensile(String emaildirigente) {
		try{
			GestioneSistema gs = GestioneSistema.getIstance();
			gs.inviaReportStatisticoMensile(emaildirigente);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void inviaElencoTurni(String emailImpiegato) {
		try{
			GestioneSistema gs = GestioneSistema.getIstance();
			gs.inviaElencoTurni(emailImpiegato);
		}catch(OperationException e) {
			System.out.println(e.getMessage());
		}
	}
}
