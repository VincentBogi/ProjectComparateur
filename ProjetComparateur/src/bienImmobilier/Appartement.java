package bienImmobilier;

import constante.ConstanteVar;

public class Appartement extends Bien {
	private boolean balcon;

	public Appartement(int surface) {
		super(surface);
		balcon = false;
	}
	
	@Override
	public String getType() {
		return ConstanteVar.bienTypeAppartement;
	}

	@Override
	public void VerifAjoutSpecifique(Piece piece) {
		if(piece instanceof Balcon) {
			balcon = true;
		}
	}
	
	public String toString() {
		return super.toString() + "Balcon : " + balcon;
	}
	
	public boolean isBalcon() {
		return balcon;
	}

	public void setBalcon(boolean balcon) {
		this.balcon = balcon;
	}

}
