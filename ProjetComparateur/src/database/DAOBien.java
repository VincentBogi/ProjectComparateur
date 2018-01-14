package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Balcon;
import bienImmobilier.Bien;
import bienImmobilier.Chambre;
import bienImmobilier.Garage;
import bienImmobilier.Jardin;
import bienImmobilier.Piece;
import bienImmobilier.SalleDeBain;
import bienImmobilier.Salon;
import bienImmobilier.Toilette;
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
        while (rset.next()){
            System.out.println();
        }
        stmt.close();
        
        return  biens;
	}

	@Override
	public boolean insert(Bien bien) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Bien bien) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Bien bien) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
