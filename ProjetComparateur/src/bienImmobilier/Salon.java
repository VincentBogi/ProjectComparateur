package bienImmobilier;

import constante.ConstanteVar;

public class Salon extends PieceInterieur {
	public Salon(int surface) {
		super(surface);
	}
	
	public Salon(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionSalon;
	}
}
