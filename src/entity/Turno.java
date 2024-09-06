package entity;
import java.time.*;
import database.TurnoDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class Turno {
	private int tipo; 			//0 turno di mattina 1 turno di sera
	private LocalDate data;		//data a cui è riferito il turno
	private int codice;
	private String usernameFarmacista;
	
	public Turno(int tipo, LocalDate data, String usernameFarmacista,int codice) {
		this.tipo=tipo;
		this.data=data;
		this.usernameFarmacista = usernameFarmacista;
		this.codice=codice;
	}
	
	public Turno(int tipo, LocalDate data, String usernameFarmacista) {
		this.tipo=tipo;
		this.data=data;
		this.usernameFarmacista = usernameFarmacista;

	}
	
	public int getTipo() {
		return this.tipo;
	}
	public LocalDate getData() {
		return this.data;
	}
	
	public String getUsernameFarmacista() {
		return this.usernameFarmacista;
	}
	
	public int getCodice() throws DAOException, DBConnectionException{
//		this.codice = TurnoDAO.getCodice(data.toString(), usernameFarmacista);
		return this.codice;
	}
	
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setUsernameFarmacista(String username) {
		this.usernameFarmacista = username;
	}
	
	public void save() throws DAOException, DBConnectionException{
		TurnoDAO.createTurno(this);
	}
	
}
