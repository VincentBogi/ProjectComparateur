package bienImmobilier;

import java.util.ArrayList;
import java.util.List;

import constante.ConstanteVar;

public abstract class Bien {
	private FabriquePiece fabriquePiece;
	private int numBien;
	private int surface;
	private int localisation;
	private int nbPiece;
	private String consoEnergie;
	private boolean jardin;
	private int nbChambre;
	private int typeTn;
	private String Parking;
	private String text;
	
	private Contrat contrat;
	private List<Piece> pieces;
	
	public Bien(int surface) {
		fabriquePiece = new FabriquePiece();
		this.surface = surface;
		localisation = 0;
		nbPiece = 0;
		consoEnergie = "";
		jardin = false;
		nbChambre = 0;
		typeTn = 0;
		Parking = "";
		text = "";
		pieces = new ArrayList<Piece>();
	}
	
	public boolean AddPiece(String fonction, int surface) {
		Boolean res = false;
		Piece piece = fabriquePiece.creerPiece(fonction, surface, this);
		if(piece != null) { // si création valide alors l'ajouter au bien
			if(piece instanceof PieceInterieur) {
				nbPiece = nbPiece + 1;
			}
			if(piece instanceof Chambre) {
				nbChambre = nbChambre + 1;
			}
			else if(piece instanceof Jardin) {
				jardin = true;
			}
			
			VerifAjoutSpecifique(piece);
			
			res = this.pieces.add(piece);
		}
		
		return res;
	}
	
	public abstract void VerifAjoutSpecifique(Piece piece) ;
	
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
		return pieces;
	}

	public void setPiece(List<Piece> piece) {
		this.pieces = piece;
	}
	
	public String getTypeContrat() {
		return contrat.getType();
	}
	
	public String toString() {
		return "BIEN		NumBien : " + numBien + " ,Type : " + getType() + ", Surface : " + surface +" ,Localisation : "
				+ localisation + " ,NbPiece : " + nbPiece +" ,ConsoEnergie : " + consoEnergie + " ,Jardin : " + jardin + " ,NbChambre : "
				+ nbChambre + " ,TypeTn : " + typeTn + " ,Parking :" + Parking + "\n" + contrat.toString();
	}
}
