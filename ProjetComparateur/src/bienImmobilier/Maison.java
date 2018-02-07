package bienImmobilier;

import constante.ConstanteVar;

public class Maison extends Bien {
	
	public Maison(int surface) {
		super(surface);
	}
	
	@Override
	public String getType() {
		return ConstanteVar.bienTypeMaison;
	}

	@Override
	public void VerifAjoutSpecifique(Piece piece) {
		// TODO Auto-generated method stub
	}

}
