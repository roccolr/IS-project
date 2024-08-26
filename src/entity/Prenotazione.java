package entity;
import java.time.*;

public class Prenotazione {
	private LocalDate data;
	private LocalTime orario;
	private Farmacia farmacia;
	private Cliente cliente;
	private Vaccino vaccino;
//	private Vaccinazione vaccinazione;
	
	public Prenotazione(LocalDate data, LocalTime orario, Farmacia farmacia, Cliente cliente, Vaccino vaccino, Vaccinazione vaccinazione) {
		this.data = data;
		this.orario = orario;
		this.farmacia = farmacia;
		this.cliente = cliente;
		this.vaccino = vaccino;
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
}
