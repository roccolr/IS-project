package entity;

import database.CartellaStatisticaDAO;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class CartellaStatistica {
	String nomeFarmacia;
	int numeroPrenotazioniGiornaliere;
	int numeroPrenotazioniSettimanali;
	int numeroPrenotazioniMensili;
	int numeroVaccinazioniGiornaliere;
	int numeroVaccinazioniSettimanali;
	int numeroVaccinazioniMensili;
	int numeroAnnullamentiGiornalieri;
	int numeroAnnullamentiSettimanali;
	int numeroAnnullamentiMensili;
	
	public CartellaStatistica(String nomeFarmacia, int nPG, int nPS, int nPM, int nVG, int nVS, int nVM, int nAG, int nAS, int nAM) {
		this.nomeFarmacia = nomeFarmacia;
		this.numeroPrenotazioniGiornaliere = nPG;
		this.numeroPrenotazioniSettimanali = nPS;
		this.numeroPrenotazioniMensili = nPM;
		this.numeroVaccinazioniGiornaliere = nVG;
		this.numeroVaccinazioniSettimanali = nVS;
		this.numeroVaccinazioniMensili = nVM;
		this.numeroAnnullamentiGiornalieri = nAG;
		this.numeroAnnullamentiSettimanali = nAS;
		this.numeroAnnullamentiMensili = nAM;
	}
	
    public String getNomeFarmacia() {
        return nomeFarmacia;
    }

    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }

    public int getNumeroPrenotazioniGiornaliere() {
        return numeroPrenotazioniGiornaliere;
    }

    public void setNumeroPrenotazioniGiornaliere(int numeroPrenotazioniGiornaliere) {
        this.numeroPrenotazioniGiornaliere = numeroPrenotazioniGiornaliere;
    }

    public int getNumeroPrenotazioniSettimanali() {
        return numeroPrenotazioniSettimanali;
    }

    public void setNumeroPrenotazioniSettimanali(int numeroPrenotazioniSettimanali) {
        this.numeroPrenotazioniSettimanali = numeroPrenotazioniSettimanali;
    }

    public int getNumeroPrenotazioniMensili() {
        return numeroPrenotazioniMensili;
    }

    public void setNumeroPrenotazioniMensili(int numeroPrenotazioniMensili) {
        this.numeroPrenotazioniMensili = numeroPrenotazioniMensili;
    }

    public int getNumeroVaccinazioniGiornaliere() {
        return numeroVaccinazioniGiornaliere;
    }

    public void setNumeroVaccinazioniGiornaliere(int numeroVaccinazioniGiornaliere) {
        this.numeroVaccinazioniGiornaliere = numeroVaccinazioniGiornaliere;
    }

    public int getNumeroVaccinazioniSettimanali() {
        return numeroVaccinazioniSettimanali;
    }

    public void setNumeroVaccinazioniSettimanali(int numeroVaccinazioniSettimanali) {
        this.numeroVaccinazioniSettimanali = numeroVaccinazioniSettimanali;
    }

    public int getNumeroVaccinazioniMensili() {
        return numeroVaccinazioniMensili;
    }

    public void setNumeroVaccinazioniMensili(int numeroVaccinazioniMensili) {
        this.numeroVaccinazioniMensili = numeroVaccinazioniMensili;
    }

    public int getNumeroAnnullamentiGiornalieri() {
        return numeroAnnullamentiGiornalieri;
    }

    public void setNumeroAnnullamentiGiornalieri(int numeroAnnullamentiGiornalieri) {
        this.numeroAnnullamentiGiornalieri = numeroAnnullamentiGiornalieri;
    }

    public int getNumeroAnnullamentiSettimanali() {
        return numeroAnnullamentiSettimanali;
    }

    public void setNumeroAnnullamentiSettimanali(int numeroAnnullamentiSettimanali) {
        this.numeroAnnullamentiSettimanali = numeroAnnullamentiSettimanali;
    }

    public int getNumeroAnnullamentiMensili() {
        return numeroAnnullamentiMensili;
    }

    public void setNumeroAnnullamentiMensili(int numeroAnnullamentiMensili) {
        this.numeroAnnullamentiMensili = numeroAnnullamentiMensili;
    }
    
    public void incrementaPrenotazioni() {
    	numeroPrenotazioniGiornaliere++;
    	numeroPrenotazioniSettimanali++;
    	numeroPrenotazioniMensili++;
    	try {
			update();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (DBConnectionException e) {
			e.printStackTrace();
		} catch (OperationException e) {
			e.printStackTrace();
		}
    } 
    
    public void incrementaVaccinazioni() {
    	numeroVaccinazioniGiornaliere++;
    	numeroVaccinazioniSettimanali++;
    	numeroVaccinazioniMensili++;
    	try {
			update();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (DBConnectionException e) {
			e.printStackTrace();
		} catch (OperationException e) {
			e.printStackTrace();
		}

    }
    
    public void incrementaAnnullamenti() {
    	numeroAnnullamentiGiornalieri++;
    	numeroAnnullamentiSettimanali++;
    	numeroAnnullamentiMensili++;
    	try {
			update();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (DBConnectionException e) {
			e.printStackTrace();
		} catch (OperationException e) {
			e.printStackTrace();
		}
    }
    
    public void update() throws DAOException, DBConnectionException, OperationException{
    	try {
    		CartellaStatisticaDAO.updateCartellaStatistica(this, nomeFarmacia);
    	}catch(DAOException e) {
    		throw new OperationException(e.getMessage());
    	}
    }
    
    public void save() throws DAOException, DBConnectionException, OperationException{
    	try {
    		CartellaStatisticaDAO.createCartellaStatistica(this);
    	}catch(DAOException e) {
    		throw new OperationException(e.getMessage());
    	}
    }
}
