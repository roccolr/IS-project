package entity;
import java.time.*;

public class Turno {
	private int tipo; 			//0 turno di mattina 1 turno di sera
	private LocalDate data;		//data a cui è riferito il turno
	public Turno(int tipo, LocalDate data) {
		this.tipo=tipo;
		this.data=data;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	public LocalDate getData() {
		return this.data;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
}
