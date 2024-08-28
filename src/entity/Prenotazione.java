package entity;
import java.time.*;
import database.PrenotazioneDAO;
import exception.*;

public class Prenotazione {
	private LocalDate data;
	private LocalTime orario;
	private Farmacia farmacia;
	private Cliente cliente;
	private Vaccino vaccino;
	private String nomeFarmacia;
	private String emailCliente;
	private String nomeVaccino;
	private int codice;
//	private Vaccinazione vaccinazione;
	
	public Prenotazione(LocalDate data, LocalTime orario, Farmacia farmacia, Cliente cliente, Vaccino vaccino) {
		this.data = data;
		this.orario = orario;
		this.farmacia = farmacia;
		this.cliente = cliente;
		this.vaccino = vaccino;
		this.nomeFarmacia = farmacia.getNome();
		this.emailCliente = cliente.getEmail();
		this.nomeVaccino = vaccino.toString();
//		this.vaccinazione = vaccinazione;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	public LocalTime getOrario() {
		return this.orario;
	}
	public Farmacia getFarmacia() {
		return this.farmacia;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public Vaccino getVaccino() {
		return this.vaccino;
	}
	public int getCodice() throws DAOException, DBConnectionException{
		this.codice = PrenotazioneDAO.getCodice(data, orario, cliente);
		return this.codice;
	}
	
	public String getNomeVaccino(){
		return this.nomeVaccino;
	}
	
	public String getEmailCliente() {
		return this.emailCliente;
	}
	
	public String getNomeFarmacia() {
		return this.nomeFarmacia;
	}
	
//	public Vaccinazione getVaccinazione() {
//		return this.vaccinazione;
//	}
	
	public void setData(LocalDate data) {
		this.data= data;
	}
	public void setOrario(LocalTime orario) {
		this.orario = orario;
	}
	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	public void setVaccino(Vaccino vaccino) {
		this.vaccino = vaccino;
	}
//	public void setVaccinazione(Vaccinazione vaccinazione) {
//		this.vaccinazione=vaccinazione;
//	}
	
	public void setNomeVaccino(String vaccino) {
		this.nomeVaccino = vaccino;
	}
	
	public void setEmailCliente (String email) {
		this.emailCliente = email;
	}
	
	public void setNomeFarmacia(String nome) {
		this.nomeFarmacia = nome;
	}
	
	public void save() throws DAOException, DBConnectionException{
		PrenotazioneDAO.createPrenotazione(this);
	}
}
