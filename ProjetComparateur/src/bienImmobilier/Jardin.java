package bienImmobilier;

import constante.ConstanteVar;

public class Jardin extends PieceExterieur {
	public Jardin(int surface) {
		super(surface);
	}
	
	public Jardin(int surface, int numPiece) {
		super(surface, numPiece);
	}

	@Override
	public String getFonction() {
		return ConstanteVar.pieceFonctionJardin;
	}
}
