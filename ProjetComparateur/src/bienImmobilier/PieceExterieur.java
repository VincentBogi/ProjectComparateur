package bienImmobilier;

import constante.ConstanteVar;

public abstract class PieceExterieur extends Piece {
	
	public PieceExterieur(int surface) {
		super(surface);
	}
	
	public PieceExterieur(int surface, int numPiece) {
		super(surface, numPiece);
	}
	
	public String getType() {
		return ConstanteVar.pieceTypeExterieur;
	}
	
	public abstract String getFonction();
}
