package bienImmobilier;

import constante.ConstanteVar;

public class Cuisine extends PieceInterieur {
	public Cuisine(int surface) {
		super(surface);
	}
	
	public Cuisine(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionCuisine;
	}
}
