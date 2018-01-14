package bienImmobilier;

import constante.ConstanteVar;

public class Appartement extends Bien {
	
	public Appartement() {
		super();
	}
	
	@Override
	public String getType() {
		return ConstanteVar.BienTypeAppartement;
	}

}
