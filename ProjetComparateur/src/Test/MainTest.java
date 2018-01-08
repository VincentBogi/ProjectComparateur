package Test;


import java.sql.*;

import database.ConnexionUnique;

public class MainTest {
	
	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		
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
	    }
	}
	
}
