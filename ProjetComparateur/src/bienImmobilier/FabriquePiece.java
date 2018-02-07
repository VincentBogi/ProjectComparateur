package bienImmobilier;

import constante.ConstanteVar;

public class FabriquePiece {
	
	public FabriquePiece() {
		
	}
	
	public Piece creerPiece(String fonction, int surface, Bien bien) {
		Piece piece;
		
		if(fonction.equals(ConstanteVar.pieceFonctionBalcon) && bien.getType().equals(ConstanteVar.bienTypeAppartement)) { // balcon si appartement
        	piece = new Balcon(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionChambre)) { // Chambre
        	piece = new Chambre(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionCuisine)) { // cuisine
        	piece = new Balcon(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionGarage)) { // Garage
        	piece = new Garage(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionJardin)) { // Jardin
        	piece = new Jardin(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionSaleDeBain)) { // salle de bain
        	piece = new SalleDeBain(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionSalon)) { // Salon
        	piece = new Salon(surface);
        }
        else if(fonction.equals(ConstanteVar.pieceFonctionToilette)){ // Toilette
        	piece = new Toilette(surface);
        }
        else {
        	piece = null;
        }
		return piece;
	}

}
