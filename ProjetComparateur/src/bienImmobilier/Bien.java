package bienImmobilier;

import java.util.List;

public abstract class Bien {
	int numBien;
	int surface;
	int localisation;
	int nbPiece;
	String consoEnergie;
	boolean jardin;
	int nbChambre;
	int typeTn;
	String Parking;
	String text;
	
	Contrat contrat;
	List<Piece> piece;
	
	public Bien() {
		
	}
	
	public abstract String getType();

	public int getNumBien() {
		return numBien;
	}

	public void setNumBien(int numBien) {
		this.numBien = numBien;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getLocalisation() {
		return localisation;
	}

	public void setLocalisation(int localisation) {
		this.localisation = localisation;
	}

	public int getNbPiece() {
		return nbPiece;
	}

	public void setNbPiece(int nbPiece) {
		this.nbPiece = nbPiece;
	}

	public String getConsoEnergie() {
		return consoEnergie;
	}

	public void setConsoEnergie(String consoEnergie) {
		this.consoEnergie = consoEnergie;
	}

	public boolean isJardin() {
		return jardin;
	}

	public void setJardin(boolean jardin) {
		this.jardin = jardin;
	}

	public int getNbChambre() {
		return nbChambre;
	}

	public void setNbChambre(int nbChambre) {
		this.nbChambre = nbChambre;
	}

	public int getTypeTn() {
		return typeTn;
	}

	public void setTypeTn(int typeTn) {
		this.typeTn = typeTn;
	}

	public String getParking() {
		return Parking;
	}

	public void setParking(String parking) {
		Parking = parking;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public List<Piece> getPiece() {
		return piece;
	}

	public void setPiece(List<Piece> piece) {
		this.piece = piece;
	}
	
	public String getTypeContrat() {
		return contrat.getType();
	}
}
