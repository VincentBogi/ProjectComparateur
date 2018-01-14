package bienImmobilier;

import constante.ConstanteVar;

public class Balcon extends PieceExterieur{
	public Balcon(int surface) {
		super(surface);
	}
	
	public Balcon(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionBalcon;
	}
}
