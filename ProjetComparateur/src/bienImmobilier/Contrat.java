package bienImmobilier;

public abstract class Contrat {
	private int reference;
	private int prix;
	
	public Contrat() {
		
	}
	
	public Contrat(int reference, int prix) {
		this.reference = reference;
		this.prix = prix;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public String toString() {
		return "CONTRAT		reference : " + reference + " prix : " + prix + " " + getType() + "\n";
	}
	
	public abstract String getType();
}
