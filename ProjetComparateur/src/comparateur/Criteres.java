package comparateur;

public class Criteres {
	// criteres principaux (éliminatoire)
	private String typeBien;
	private String typeContrat;
	private int typeTn;
	private int surfaceMin;
	private int SurfaceMax;
	private int prixMin;
	private int prixMax;
	
	//criteres de trie
	private int nbPieceInterieur;
	private String consoEnergie;
	private boolean balcon;
	private boolean jardin;
	private int nbChambre;
	private int localisation;
	private String parcking;
	
	public Criteres() {
		
	}

	public String getTypeBien() {
		return typeBien;
	}

	public void setTypeBien(String typeBien) {
		this.typeBien = typeBien;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public int getTypeTn() {
		return typeTn;
	}

	public void setTypeTn(int typeTn) {
		this.typeTn = typeTn;
	}

	public int getSurfaceMin() {
		return surfaceMin;
	}

	public void setSurfaceMin(int surfaceMin) {
		this.surfaceMin = surfaceMin;
	}

	public int getSurfaceMax() {
		return SurfaceMax;
	}

	public void setSurfaceMax(int surfaceMax) {
		SurfaceMax = surfaceMax;
	}

	public int getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(int prixMin) {
		this.prixMin = prixMin;
	}

	public int getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(int prixMax) {
		this.prixMax = prixMax;
	}

	public int getNbPieceInterieur() {
		return nbPieceInterieur;
	}

	public void setNbPieceInterieur(int nbPieceInterieur) {
		this.nbPieceInterieur = nbPieceInterieur;
	}

	public String getConsoEnergie() {
		return consoEnergie;
	}

	public void setConsoEnergie(String consoEnergie) {
		this.consoEnergie = consoEnergie;
	}

	public boolean isBalcon() {
		return balcon;
	}

	public void setBalcon(boolean balcon) {
		this.balcon = balcon;
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

	public int getLocalisation() {
		return localisation;
	}

	public void setLocalisation(int localisation) {
		this.localisation = localisation;
	}

	public String getParcking() {
		return parcking;
	}

	public void setParcking(String parcking) {
		this.parcking = parcking;
	}
	
	
	
}
