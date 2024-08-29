package entity;

import exception.DAOException;
import exception.DBConnectionException;
import database.ClienteDAO;

public class Cliente {
//	public static final int dimTestoAllergie = 100;
	private String nome;
	private String cognome;
	private String email;
	private String allergie;
	private String datiAnamnestici = null;
	
	public Cliente(String nome, String cognome, String email, String allergie) {
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.allergie=allergie;
	}
	
	public String getNome() {
		return this.nome;
	}
	public String getCognome() {
		return this.cognome;
	}
	public String getEmail() {
		return this.email;
	}
	public String getAllergie() {
		return this.allergie;
	}
	public String getDatiAnamnestici() {
		return this.datiAnamnestici;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public void setAllergie(String allergie) {
		this.allergie=allergie;
	}
	public void setDatiAnamnestici(String datiAnamnestici) {
		this.datiAnamnestici = datiAnamnestici;
	}
	public void save()throws DAOException, DBConnectionException{
		ClienteDAO.createCliente(this);
	}
}
