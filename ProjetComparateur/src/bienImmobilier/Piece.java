package bienImmobilier;

public abstract class Piece {
	private int surface;
	private int numPiece;
	
	public Piece(int surface) {
		this.surface = surface;
		numPiece = -1;
	}
	
	public Piece(int surface, int numPiece) {
		this.surface = surface;
		this.numPiece = numPiece;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}
	
	public abstract String getFonction();
	
	public abstract String getType();
	
	public String toString() {
		return "piece de " + surface + "m carré, fonction : " + getFonction() + ", type : " + getType() + "\n";
	}

	public int getNumPiece() {
		return numPiece;
	}

	public void setNumPiece(int numPiece) {
		this.numPiece = numPiece;
	}
	
	
}
