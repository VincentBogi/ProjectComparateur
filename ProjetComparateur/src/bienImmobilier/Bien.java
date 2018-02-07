package bienImmobilier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import comparateur.Criteres;
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
	private String parking;
	private String text;
	
	private Contrat contrat;
	private List<Piece> pieces;
	private int valeurDeProximite;
	
	public Bien(int surface) {
		fabriquePiece = new FabriquePiece();
		this.surface = surface;
		localisation = 0;
		nbPiece = 0;
		consoEnergie = null;
		jardin = false;
		nbChambre = 0;
		typeTn = 0;
		parking = null;
		text = null;
		pieces = new ArrayList<Piece>();
		valeurDeProximite = 0;
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
	
	public void evaluerValeurDeProximiter(Criteres criteres, HashMap<Integer, List<Integer>> hashLocation) {
		int i = 0;
		i = i + evaluerNbDePiece(criteres.getNbPieceInterieur());
		i = i + evaluerNbChambre(criteres.getNbChambre());
		i = i + evaluerLocalisation(criteres.getLocalisation(), hashLocation);
		i = i + evaluerParking(criteres.getParcking());
		i = i + evaluerConsoEnergie(criteres.getConsoEnergie());
		if(criteres.isJardin()) {
			i = i + evaluerJardin(criteres.isJardin());
		}
		
		valeurDeProximite = i;
	}
	
	public int evaluerNbDePiece(int critereNbPiece) {
		int i = 0;
		
		if(nbPiece == critereNbPiece) { // si nb de piece respecté
			i = 2;
		}
		else if ((nbPiece + 1) == critereNbPiece) { // si nombre 
			i = 1;
		}
		
		return i;
	}
	
	public int evaluerNbChambre(int critereNbChambre) {
		int i = 0;
		if(nbChambre == critereNbChambre) {
			i = 3;
		}
		else if((nbChambre + 1) == critereNbChambre) {
			i = 1;
		}
		
		return i;
	}
	
	public int evaluerLocalisation(int critereLocalisation, HashMap<Integer, List<Integer>> hashLocalisation) {
		int res = 0;
		if(critereLocalisation == localisation) {
			res = 5;
		}
		else if(hashLocalisation.get(critereLocalisation).contains(localisation))
		{
			res = 2;
		}
		
		return res;	
	}
	
	public int evaluerParking(String critereParking) {
		int valueBien = ConstanteVar.hashMapParking.get(parking);
		int valueCritere = ConstanteVar.hashMapParking.get(critereParking);
		int res = 0;
		
		if(valueCritere >= valueBien) {
			res = 2;
		}
		else if(valueCritere == valueBien - 1) {
			res = 1;
		}
		
		return res;
	}
	
	public int evaluerConsoEnergie(String critereConsoEnergie) {
		//TODO
		return 0;
	}
	
	public int evaluerJardin(boolean critereJardin) {
		//TODO
		return 0;
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
		return parking;
	}
	
	

	public int getValeurDeProximite() {
		return valeurDeProximite;
	}


	public void setParking(String parking) {
		this.parking = parking;
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
				+ nbChambre + " ,TypeTn : " + typeTn + " ,Parking :" + parking + " ,Valeur de porcimiter : " + valeurDeProximite + 
				"\n" + "valeur proximité : " + valeurDeProximite + "\n"+ contrat.toString();
	}
}
