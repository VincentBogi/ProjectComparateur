package bienImmobilier;

import constante.ConstanteVar;

public class Toilette extends PieceInterieur {
	public Toilette(int surface) {
		super(surface);
	}
	
	public Toilette(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionToilette;
	}

}
