package bienImmobilier;

public class Vente extends Contrat {
	
	public Vente() {
		super();
	}
	
	public Vente(int reference, int prix) {
		super(reference, prix);
	}

	@Override
	public String getType() {
		return "vente";
	}
	
	
}
