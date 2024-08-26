package entity;
import java.util.*;

public class Farmacista {
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private boolean dipendente;
	private ArrayList<Turno> Turni;
	
	public Farmacista(String nome, String cognome, String username, String password, boolean dipendente) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dipendente = dipendente;
		this.Turni = new ArrayList<>();
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
	public void addTurno(Turno turno) {
		this.Turni.add(turno);
	}
	
}
