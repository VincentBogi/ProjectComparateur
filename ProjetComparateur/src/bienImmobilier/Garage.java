package bienImmobilier;

import constante.ConstanteVar;

public class Garage extends PieceExterieur{
	public Garage(int surface) {
		super(surface);
	}
	
	public Garage(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionGarage;
	}
}
