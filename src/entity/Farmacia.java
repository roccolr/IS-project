package entity;
import java.util.*;


public class Farmacia {
	private String nome;
	private String indirizzo;
	private String email;
	private CapoFarmacia capoFarmacia = null;
	private ArrayList<Farmacista> farmacisti;
	
	
	public Farmacia(String nome, String indirizzo, String email) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.email = email;
		this.farmacisti = new ArrayList<>();
	}

	
	public String getNome() {
		return this.nome;
	}
	public String getIndirizzo() {
		return this.indirizzo;
	}
	public String getEmail() {
		return this.email;
	}
	public CapoFarmacia getCapoFarmacia() {
		return this.capoFarmacia;
	}
	public ArrayList<Farmacista> getFarmacisti(){
		return this.farmacisti;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setCapoFarmacia(CapoFarmacia capoFarmacia) {
		this.capoFarmacia = capoFarmacia;
	}
	public void setFarmacisti(ArrayList<Farmacista> farmacisti) {
		this.farmacisti = farmacisti;
	}
	
}
