package bienImmobilier;

import constante.ConstanteVar;

public class SalleDeBain extends PieceInterieur {
	public SalleDeBain(int surface) {
		super(surface);
	}
	
	public SalleDeBain(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionSaleDeBain;
	}
}
