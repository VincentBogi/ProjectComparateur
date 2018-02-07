package Test;


import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import bienImmobilier.Appartement;
import bienImmobilier.Bien;
import bienImmobilier.Contrat;
import bienImmobilier.Location;
import bienImmobilier.Piece;
import bienImmobilier.Salon;
import bienImmobilier.Vente;
import comparateur.Comparateur;
import comparateur.Criteres;
import constante.ConstanteVar;
import database.ConnexionUnique;
import database.DAOBien;
import database.DAOContrat;
import database.DAOPiece;
import fenetre.MaFenetre;

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
		/*
		Contrat contrat = new Location(9, 720);
		Bien bien = new Appartement(60);
		bien.setConsoEnergie("A");
		bien.setLocalisation(13007);
		bien.setContrat(contrat);
		bien.setParking(ConstanteVar.parkingPublic);
		bien.setText("appartement très classe");
		bien.setTypeTn(3);
		bien.setNumBien(13);
		
		System.out.println(bien);
		
		bien.AddPiece(ConstanteVar.pieceFonctionSaleDeBain, 6);
		bien.AddPiece(ConstanteVar.pieceFonctionChambre, 9);
		bien.AddPiece(ConstanteVar.pieceFonctionChambre, 13);
		bien.AddPiece(ConstanteVar.pieceFonctionSalon, 12);
		bien.AddPiece(ConstanteVar.pieceFonctionCuisine, 12);
		bien.AddPiece(ConstanteVar.pieceFonctionToilette, 3);
		
		System.out.println(bien);
		
		DAOBien daoBien = DAOBien.getInstance();
		daoBien.insert(bien);*/
		
		/* Test recup selon citere principaux
		Criteres criteres = new Criteres();
		criteres.setSurfaceMax(30);
		criteres.setSurfaceMin(20);
		criteres.setPrixMax(550000);
		criteres.setPrixMin(450000);
		criteres.setTypeContrat(ConstanteVar.contratTypeLocation);
		criteres.setTypeBien(ConstanteVar.bienTypeAppartement);
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
		}*/
		
		
		/*Criteres criteres = new Criteres();
		criteres.setSurfaceMax(70);
		criteres.setSurfaceMin(10);
		criteres.setPrixMax(800);
		criteres.setPrixMin(400);
		criteres.setTypeContrat(ConstanteVar.contratTypeLocation);
		criteres.setTypeBien(ConstanteVar.bienTypeAppartement);
		criteres.setTypeTn(3);
		criteres.setConsoEnergie("B");
		criteres.setJardin(false, 0, 0);
		criteres.setLocalisation(13008);
		criteres.setNbChambre(2);
		criteres.setNbPieceInterieur(5);
		criteres.setParcking(ConstanteVar.parkingPublicSurveiller);
		
		Comparateur comparateur = new Comparateur(criteres);
		comparateur.comparer();*/
		
		MaFenetre fenetre = new MaFenetre();
		fenetre.initFenetre();
		fenetre.setVisible(true);
	}
	
}
