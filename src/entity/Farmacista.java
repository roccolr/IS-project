package entity;
import java.util.*;

import database.FarmacistaDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class Farmacista {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private boolean dipendente;
	private String nomeFarmacia;
	private ArrayList<Turno> turni;
	
	public Farmacista(String nome, String cognome, String username, String password, boolean dipendente, String nomeFarmacia) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dipendente = dipendente;
		this.nomeFarmacia = nomeFarmacia;
		this.turni = null;
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
	public void setDipendente(boolean dipendente) {
		this.dipendente = dipendente;
	}
	public void setNomeFarmacia(String nomeFarmacia){
		this.nomeFarmacia = nomeFarmacia;
	}
	public void setTurni(ArrayList<Turno> turni) {
		this.turni = turni;
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
	public boolean isDipendente() {
		return this.dipendente;
	}
	public String getNomeFarmacia(){
		return this.nomeFarmacia;
	}
	public ArrayList<Turno> getTurni(){
		return this.turni;
	}
	public void save() throws DAOException, DBConnectionException{
		FarmacistaDAO.createFarmacista(this);
//		System.out.println("debug");
	}
}
