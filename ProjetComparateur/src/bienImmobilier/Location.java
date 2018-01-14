package bienImmobilier;

public class Location extends Contrat {

	public Location() {
		super();
	}
	
	public Location(int reference, int prix) {
		super(reference, prix);
	}

	@Override
	public String getType() {
		
		return "location";
	}
	
}
