package bienImmobilier;

import constante.ConstanteVar;

public class Maison extends Bien {
	
	public Maison() {
		super();
	}
	
	@Override
	public String getType() {
		return ConstanteVar.BienTypeMaison;
	}

}
