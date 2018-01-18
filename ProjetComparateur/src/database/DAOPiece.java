package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Balcon;
import bienImmobilier.Chambre;
import bienImmobilier.Contrat;
import bienImmobilier.Garage;
import bienImmobilier.Jardin;
import bienImmobilier.Location;
import bienImmobilier.Piece;
import bienImmobilier.SalleDeBain;
import bienImmobilier.Salon;
import bienImmobilier.Toilette;
import bienImmobilier.Vente;
import constante.ConstanteVar;

public class DAOPiece implements DAOPieceInterface {

	private static DAOPiece instance;
	private static Connection connection;
	
    private DAOPiece() {
    	
    }

    public static DAOPiece getInstance() throws SQLException {
        if(instance==null)
        {
            instance = new DAOPiece();
            connection = ConnexionUnique.getInstance().getConnection();
        }
        return instance;
    }
	
	
	@Override
	public List<Piece> findByNumBien(int numBien) throws SQLException {
		Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM Piece WHERE numBien=" + numBien);
        
        List<Piece> pieces = new ArrayList<Piece>();
        Piece piece;
        String type;
        int surface;
        int numPiece;
        while (rset.next()){
            type = rset.getString(4);
            surface = rset.getInt(2);
            numPiece = rset.getInt(1);
            if(type.equals(ConstanteVar.pieceFonctionBalcon)) { // balcon
            	piece = new Balcon(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionChambre)) { // Chambre
            	piece = new Chambre(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionCuisine)) { // cuisine
            	piece = new Balcon(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionGarage)) { // Garage
            	piece = new Garage(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionJardin)) { // Jardin
            	piece = new Jardin(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionSaleDeBain)) { // salle de bain
            	piece = new SalleDeBain(surface, numPiece);
            }
            else if(type.equals(ConstanteVar.pieceFonctionSalon)) { // Salon
            	piece = new Salon(surface, numPiece);
            }
            else { // Toilette
            	piece = new Toilette(surface, numPiece);
            }
            pieces.add(piece);
        }
        
        stmt.close();
        
        return pieces;
	}

	@Override
	public boolean insert(Piece piece, int numBien) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		int resContrat = stmt.executeUpdate("INSERT INTO Piece (surface, fonction, type, numBien) VALUES (" + piece.getSurface() + ", '" + piece.getFonction() + "', '" + piece.getType() + "', " + numBien + ")");
		stmt.close();
		
		if (resContrat == 1) {
            isSuccess = true;
		}
		

        return isSuccess;
	}

	@Override
	public boolean delete(Piece piece) throws SQLException { // delete by numPiece
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("DELETE FROM Piece WHERE numPiece=" + piece.getNumPiece());
		
		if (rsetModule >= 1) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}
	
	@Override
	public boolean deleteByNumBien(int numBien) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("DELETE FROM Piece WHERE numBien=" + numBien);
		
		if (rsetModule >= 1) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}


	@Override
	public boolean update(Piece piece) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("UPDATE Piece SET surface=" + piece.getSurface() + " WHERE numPiece=" + piece.getNumPiece() );
		
		if (rsetModule >= 1) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}
}
