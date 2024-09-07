package boundary;
import control.GestioneSistema;
import entity.Turno;
import exception.OperationException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Random;

public class BCapoFarmacia {
	
	public boolean login(String u, String p) throws OperationException {
		if(u.length()==0 || u.length()>50) {
			throw new OperationException("Inserire un username valido");
		}
		if(p.length()==0 || p.length()>50) {
			throw new OperationException("Inserire una password valida");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		return gS.login(u,p);
	}
	
	private String generaUsername(String nome, String cognome) {
		Random r = new Random();
		String username = nome + cognome + r.nextInt(1000)+"Farmacista";
		return username;
	}
	
	private String generaPassword(String nome, String cognome) {
		Random r = new Random();
		String password = nome + cognome + r.nextInt(8000);
		return password;
	}
	
	private String generaEmail(String nome, String cognome) {
		Random r = new Random();
		String email = nome+cognome+r.nextInt(10)+"@italfarmaciefarmacisti.it";
		return email;
	}
	
	public ArrayList<LocalDate>getGiorniSettimanaProssima(){
		LocalDate today = LocalDate.now();
		LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		LocalDate giorno = nextMonday;
		ArrayList<LocalDate> giorni=new ArrayList<LocalDate>();
		
		for(int i=0; i<7; i++) {
			giorni.add(giorno.plusDays(i));
		}
//		System.out.println("GIORNI INSERITI "+ giorni.size());
		return giorni;
	}
	
	public ArrayList<Turno>getTurniSettimana(String nomeFarmacia) throws OperationException{
		ArrayList<Turno> turni=null;
		if(nomeFarmacia.isEmpty() || nomeFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		turni=gS.getTurniSettimana(nomeFarmacia);
		return turni;
	}
	
	public String getNomeFarmacia(String usernameCapoFarmacia) throws OperationException{
		String nomeFarmacia;
		if(usernameCapoFarmacia.isEmpty() || usernameCapoFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		nomeFarmacia=gS.getNomeFarmacia(usernameCapoFarmacia);
		return nomeFarmacia;
	}
	
	public void registraFarmacista(String nome, String cognome,boolean dipendente,String usernameCapoFarmacia) throws OperationException {
		if(nome.isEmpty() || nome.length()>50) {
			throw new OperationException("Errore: inserire un nome valido...");
		}
		if(cognome.isEmpty() || cognome.length()>50) {
			throw new OperationException("Errore: inserire un cognome valido...");
		}

		GestioneSistema gS = GestioneSistema.getIstance();
		gS.registraFarmacista(nome, cognome, generaUsername(nome,cognome), generaPassword(nome,cognome), usernameCapoFarmacia,generaEmail(nome, cognome), dipendente);
	}
	public void cancellaAppuntamento(int codicePrenotazione) throws OperationException {
		GestioneSistema gS = GestioneSistema.getIstance();
		gS.cancellaAppuntamento(codicePrenotazione);
	}
	
	public void inserisciTurni(ArrayList <String>usernameFarmacisti,String nomeFarmacia) throws OperationException {
		if(nomeFarmacia.isEmpty() || nomeFarmacia.length()>50) {
			throw new OperationException("Errore: inserire un nome per la Farmacia valido...");
		}
		GestioneSistema gS = GestioneSistema.getIstance();
		gS.inserisciTurniSettimana(usernameFarmacisti,nomeFarmacia);
	}
}
