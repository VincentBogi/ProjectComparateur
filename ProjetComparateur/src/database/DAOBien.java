package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Appartement;
import bienImmobilier.Bien;
import bienImmobilier.Contrat;
import bienImmobilier.Location;
import bienImmobilier.Maison;
import bienImmobilier.Piece;
import bienImmobilier.Vente;
import comparateur.Criteres;
import constante.ConstanteVar;

public class DAOBien implements DAOBienInterface {
	
	private static DAOBien instance;
	private static Connection connection;
	
    private DAOBien() {
    	
    }

    public static DAOBien getInstance() throws SQLException {
        if(instance==null)
        {
            instance = new DAOBien();
            connection = ConnexionUnique.getInstance().getConnection();
        }
        return instance;
    }
	
	@Override
	public List<Bien> findByCriteres(Criteres criteres) throws SQLException {
		Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM Bien B, Contrat C  WHERE B.reference=C.reference AND typeBien='"
		+ criteres.getTypeBien() + "' AND C.type='" + criteres.getTypeContrat() + "' AND typeTn=" + criteres.getTypeTn() + 
		" AND C.prix<=" + criteres.getPrixMax() + " AND C.prix>=" + criteres.getPrixMin() + 
		" AND surface<=" + criteres.getSurfaceMax() + " AND surface>=" + criteres.getSurfaceMin());
        
        List<Bien> biens = new ArrayList<Bien>();
        Bien bien;
        Contrat contrat;
        Appartement apparte;
        DAOPiece daoPiece = DAOPiece.getInstance();
        while (rset.next()) { // construction d'un bien à chaque itération
            if(rset.getString(2).equals(ConstanteVar.bienTypeAppartement)) { // appartement
            	apparte = new Appartement(rset.getInt(3));   ///TODO a voire si peut mieu faire avec le cour poo plus de généricité
            	apparte.setBalcon(rset.getBoolean(13));
            	bien = apparte;
            }
            else {	// maison
            	bien = new Maison(rset.getInt(3));
            }
            bien.setPiece(daoPiece.findByNumBien(rset.getInt(1)));
            bien.setNumBien(rset.getInt(1));
            bien.setLocalisation(rset.getInt(4));
            bien.setNbPiece(rset.getInt(5));
            bien.setConsoEnergie(rset.getString(6));
            bien.setJardin(rset.getBoolean(7));
            bien.setNbChambre(rset.getInt(8));
            bien.setTypeTn(rset.getInt(9));
            bien.setParking(rset.getString(11));
            bien.setText(rset.getString(12)); 
            
            if(rset.getString(16).equals(ConstanteVar.contratTypeLocation)) {
            	contrat = new Location(rset.getInt(14), rset.getInt(15));
            }
            else {
            	contrat = new Vente(rset.getInt(14), rset.getInt(15));
            }
            bien.setContrat(contrat);
            biens.add(bien);
        }
        stmt.close();
        
        return  biens;
	}

	@Override
	public boolean insert(Bien bien) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		boolean isBalcon = false;
		
		if(bien.getType().equals(ConstanteVar.bienTypeAppartement)) {
			Appartement apparte = (Appartement)bien;
			isBalcon = apparte.isBalcon();
		}
		
		DAOContrat daoContrat = DAOContrat.getInstance();
		if(daoContrat.insert(bien.getContrat())) {	// si l'ajout du contrat dans la BD réussi car possible seulement si le contrat est créé
			int resContrat = stmt.executeUpdate("INSERT INTO Bien (typeBien,  surface, localisation, nbPiece, consoEnergie, jardin, nbChambre, typeTn, reference, parking, text, balcon) VALUES "
					+ "('" + bien.getType()+ "', " + bien.getSurface() + ", " + bien.getLocalisation() + ", " + bien.getNbPiece() + ", '" + bien.getConsoEnergie() + "', " 
					+ bien.isJardin() + ", " + bien.getNbChambre() + ", " + bien.getTypeTn() + ", " + bien.getContrat().getReference() + ", '" + bien.getParking() + "', '" 
					+ bien.getText() + "', " + isBalcon +  ")");
			stmt.close();
			
			if(resContrat == 1) {	// si L'ajout du bien dans la BD a réussit
				isSuccess = true;
				
				DAOPiece daoPiece = DAOPiece.getInstance();  // ajout des piece du bien dans la BD
				for(Piece piece : bien.getPiece()) {
					isSuccess = isSuccess & daoPiece.insert(piece, bien.getNumBien());
				}
			}
		}

        return isSuccess;
	}

	@Override
	public boolean delete(Bien bien) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();		
		boolean isBalcon = false;
		
		if(bien.getType().equals(ConstanteVar.bienTypeAppartement)) {
			Appartement apparte = (Appartement)bien;
			isBalcon = apparte.isBalcon();
		}
		
		DAOContrat daoContrat = DAOContrat.getInstance();
		DAOPiece daoPiece = DAOPiece.getInstance();
		
		if (daoPiece.deleteByNumBien(bien.getNumBien()) && daoContrat.delete(bien.getContrat())) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}

	@Override
	public boolean update(Bien bien) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("UPDATE Bien SET typeBien='" + bien.getType() + "', surface=" + bien.getSurface()
		+ ", localisation=" + bien.getLocalisation() + ", nbPiece=" + bien.getNbPiece() + ", consoEnergie='" + bien.getConsoEnergie()
		+ "', jardin=" + bien.isJardin() + ", nbChambre=" + bien.getNbChambre() + ", typeTn=" + bien.getTypeTn()  + ", parking='" 
		+ bien.getParking() + "', text='" + bien.getText() + "' WHERE reference = " + bien.getContrat().getReference() );
		
		stmt.close();

		
		DAOContrat daoContrat = DAOContrat.getInstance();
		DAOPiece daoPiece = DAOPiece.getInstance();
		List<Piece> pieces = bien.getPiece();
		
		daoContrat.update(bien.getContrat());
		
		isSuccess = true;
		for(Piece piece : pieces) {
			if(piece.getNumPiece() >= 0) {
				isSuccess = isSuccess && daoPiece.update(piece);
			}
			else {
				isSuccess = isSuccess && daoPiece.insertWitheNumPiece(piece, bien.getNumBien());
			}
		}

		
		
		return isSuccess;
	}
	
	public int getNumBienDispo() throws SQLException {
		int numBien = -1;
		Statement stmt = connection.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT MAX( numBien ) FROM  Bien " );
		rset.first();
		numBien = rset.getInt(1);
		stmt.close();
		
		return numBien + 1;
	}

}
