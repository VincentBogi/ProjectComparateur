package bienImmobilier;

import constante.ConstanteVar;

public class Chambre extends PieceInterieur{
	
	public Chambre(int surface) {
		super(surface);
	}
	
	public Chambre(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionChambre;
	}
	
	
}
