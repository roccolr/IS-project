package control;

public class GestioneSistema {
	private static GestioneSistema gs = null;
	
	protected GestioneSistema() {}
	
	public GestioneSistema getIstance() {
		if(gs == null) {
			gs = new GestioneSistema();
		}
		return gs;
	}
	
	public void prenotaVaccino() {}
	public void registraCapoFarmacia() {}
	public void cancellaAppuntamento() {}
	public void inserisciTurniSettimana() {}
	public void stampaAppuntamenti() {}
	public void inserisciDatiVaccinazione() {}
	public void motivaVaccinazioneNonEffettuata() {}
	public void inviaReportStatistici() {}
	public void inviaElencoTurni() {}
	private void inviaMail() {}
	
}
