package entity;

import database.VaccinazioneDAO;
import exception.*;

public class Vaccinazione {
	private String esito;
	private String motivazione;
	private int codice;
	private int codicePrenotazione = -1;
	private String usernameFarmacista;
	private String nomeFarmacia;
	private Prenotazione prenotazione;
	private Farmacista farmacista;
	
	public Vaccinazione(String esito, String motivazione, Prenotazione prenotazione, Farmacista farmacista) {
		this.esito = esito;
		this.motivazione = motivazione;
		this.prenotazione = prenotazione;
		this.farmacista = farmacista;
	}
	
	public Vaccinazione(int codicePrenotazione, String nomeFarmacia) {
		this.esito = null;
		this.motivazione = null;
		this.codice = -1;
		this.usernameFarmacista = null;
		this.prenotazione = null;
		this.farmacista = null;
		this.nomeFarmacia = nomeFarmacia;
		this.codicePrenotazione = codicePrenotazione;
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
//	public String getNomeFarmacista() {
//		return this.farmacista.getNome();
//	}
	
	public Farmacista getFarmacista() {
		return this.farmacista;
	}
	
	public String getUsernameFarmacista() throws DAOException, DBConnectionException {
//		this.usernameFarmacista = VaccinazioneDAO.getUsernameFarmacista(this);
		return this.usernameFarmacista;
	}
	
	public String getNomeFarmacia()throws DAOException, DBConnectionException  {
//		this.nomeFarmacia = VaccinazioneDAO.getNomeFarmacia(this);
		return this.nomeFarmacia;
	}
	
	public int getCodice() throws DAOException, DBConnectionException{
//		this.codice = VaccinazioneDAO.getCodice(this);
		return this.codice;
	}
	
	public int getCodicePrenotazione() throws DAOException, DBConnectionException {
		if(this.codicePrenotazione == -1) {
			this.codicePrenotazione = VaccinazioneDAO.getCodicePrenotazione(this);	
		}
		return this.codicePrenotazione;
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
	
	public void setCodice(int codice) {
		this.codice = codice;
	}
	
	public void setCodicePrenotazione(int codicePrenotazione) {
		this.codicePrenotazione = codicePrenotazione;
	}
	
	public void setUsernameFarmacista(String usernameFarmacista) {
		this.usernameFarmacista = usernameFarmacista;
	}

	public void setNomeFarmacia(String nomeFarmacia) {
		this.nomeFarmacia = nomeFarmacia;
	}
	
	public  void save() throws DAOException, DBConnectionException{
		VaccinazioneDAO.createVaccinazione(this.codicePrenotazione, this.nomeFarmacia);
	}
	
}
