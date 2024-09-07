package entity;

import database.CapoFarmaciaDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class CapoFarmacia {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	
	public CapoFarmacia(String nome, String cognome, String username, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return this.nome;
	}
	public String getCognome() {
		return this.cognome;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public void save() throws DAOException, DBConnectionException{
		CapoFarmaciaDAO.createCapoFarmacia(this);
	}
}
