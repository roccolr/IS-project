package entity;

public class Vaccinazione {
	private String esito;
	private String motivazione;
	private Prenotazione prenotazione;
	
	public Vaccinazione(String esito, String motivazione, Prenotazione prenotazione) {
		this.esito = esito;
		this.motivazione = motivazione;
		this.prenotazione = prenotazione;
	}
	
	public String getEsito() {
		return this.esito;
	}
	public String getMotivazione() {
		return this.motivazione;
	}
	public Prenotazione getPrenotazione() {
		return this.prenotazione;
	}
	
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}
}
