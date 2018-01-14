package bienImmobilier;

import constante.ConstanteVar;

public abstract class PieceInterieur extends Piece {

	public PieceInterieur(int surface) {
		super(surface);
	}
	
	public PieceInterieur(int surface, int numPiece) {
		super(surface, numPiece);
	}
	
	public String getType() {
		return ConstanteVar.pieceTypeInterieur;
	}
	
	public abstract String getFonction();
}
