package Test;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Appartement;
import bienImmobilier.Bien;
import bienImmobilier.Contrat;
import bienImmobilier.Location;
import bienImmobilier.Piece;
import bienImmobilier.Salon;
import bienImmobilier.Vente;
import comparateur.Criteres;
import constante.ConstanteVar;
import database.ConnexionUnique;
import database.DAOBien;
import database.DAOContrat;
import database.DAOPiece;

public class MainTest {
	
	public static void main(String[] args) throws SQLException {
		/*Connection conn = null;
		
		String req = "SELECT prix " +
	            "FROM Contrat " +
	            "WHERE reference = 1000";
		try {
			System.out.println("Connexion a la BD :");
	        ConnexionUnique Instance = ConnexionUnique.getInstance();
	        conn = Instance.getConnection();
			System.out.println("La connection est fait ça devrait donc marcher \n");
			Statement stmt = conn.createStatement();
	        // Execution de la requete
	        System.out.println("Execution de la requete : " + req );
	        ResultSet rset = stmt.executeQuery(req);
	        // Affichage du resultat
	        while (rset.next()){
	            System.out.println(rset.getInt("prix"));
	        }
	        // Fermeture de l'instruction (liberation des ressources)
	        stmt.close();
	        System.out.println("\nOk.\n");
		} 
		catch (SQLException e) {
	        e.printStackTrace();// Arggg!!!
	        System.out.println(e.getMessage() + "\n");
	    } 
		finally {
	        if (conn != null) {
	            // Deconnexion de la base de donnees
	            conn.close();
	        }
	    }*/
		
		/*DAOPiece daoPiece = DAOPiece.getInstance();
		List<Piece> pieces = new ArrayList<>();

		pieces = daoPiece.findByNumBien(1);
		
		daoPiece.deleteByNumBien(1);*/
		
		
		/* Test critere
		DAOBien daoBien = DAOBien.getInstance();
		Criteres criteres = new Criteres();
		criteres.setSurfaceMax(90);
		criteres.setSurfaceMin(30);
		criteres.setPrixMax(550000);
		criteres.setPrixMin(450000);
		criteres.setTypeContrat(ConstanteVar.contratTypeLocation);
		criteres.setTypeBien(ConstanteVar.BienTypeAppartement);
		criteres.setTypeTn(4);
		
		List<Bien> biens = daoBien.findByCriteres(criteres);
		
		for( Bien bien : biens) {
			bien.display();
		}*/
		
		/*Test DAOBien */
		/*Contrat contrat = new Location(500, 490000);
		Bien bien = new Appartement(22);
		bien.setConsoEnergie("A");
		bien.setLocalisation(13004);
		bien.setContrat(contrat);
		bien.setParking("priver");
		bien.setText("booooooob");
		bien.setTypeTn(2);
		bien.setNumBien(5);
		
		System.out.println(bien);
		
		bien.AddPiece(ConstanteVar.pieceFonctionSaleDeBain, 6);
		bien.AddPiece(ConstanteVar.pieceFonctionChambre, 18);
		bien.AddPiece(ConstanteVar.pieceFonctionBalcon, 18);
		
		System.out.println(bien);
		
		DAOBien daoBien = DAOBien.getInstance();
		daoBien.update(bien);*/
		
		/* Test recup selon citere principaux */
		Criteres criteres = new Criteres();
		criteres.setSurfaceMax(30);
		criteres.setSurfaceMin(20);
		criteres.setPrixMax(550000);
		criteres.setPrixMin(450000);
		criteres.setTypeContrat(ConstanteVar.contratTypeLocation);
		criteres.setTypeBien(ConstanteVar.BienTypeAppartement);
		criteres.setTypeTn(2);
		
		DAOBien daoBien = DAOBien.getInstance();
		List<Bien> biens = daoBien.findByCriteres(criteres);
		
		for(Bien bien : biens) {
			System.out.println(bien);
			bien.getContrat().setPrix(492000);
			bien.setConsoEnergie("B");
			Piece piece = bien.getPiece().get(0);
			piece.setSurface(10);
			System.out.println(bien);
			daoBien.update(bien);
		}
	}
	
}
