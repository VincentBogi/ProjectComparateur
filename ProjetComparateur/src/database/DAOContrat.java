package database;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import bienImmobilier.Contrat;
import bienImmobilier.Location;
import bienImmobilier.Vente;
import constante.ConstanteVar;

public class DAOContrat implements DAOContratInterface {
	
	private static DAOContrat instance;
	private static Connection connection;
	
    private DAOContrat() {
    	
    }

    public static DAOContrat getInstance() throws SQLException {
        if(instance==null)
        {
            instance = new DAOContrat();
            connection = ConnexionUnique.getInstance().getConnection();
        }
        return instance;
    }
	
	@Override
	public Contrat findByReference(int reference) throws SQLException {
		Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM Contrat WHERE reference=" + "'" + reference + "'");
        
        Contrat contrat = null;
        rset.first();
        if(rset.getString(3).equals(ConstanteVar.contratTypeLocation)) {
        	contrat = new Location();
        }
        else {
        	contrat = new Vente();
        }
        contrat.setReference(rset.getInt(1));
        contrat.setPrix(rset.getInt(2));
        stmt.close();
        
        return contrat;
	}

	@Override
	public boolean insert(Contrat contrat) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		int resContrat = stmt.executeUpdate("INSERT INTO Contrat (reference, prix, type) VALUES (" + contrat.getReference() + ", " + contrat.getPrix() + ", '" + contrat.getType() + "')");
		stmt.close();
		
		if (resContrat == 1) {
            isSuccess = true;
		}

        return isSuccess;
	}
	

	@Override
	public boolean delete(Contrat contrat) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("DELETE FROM Contrat WHERE reference =  " + contrat.getReference());
		
		if (rsetModule >= 1) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}

	@Override
	public boolean update(Contrat contrat) throws SQLException {
		boolean isSuccess = false;
		Statement stmt = connection.createStatement();
		
		int rsetModule = stmt.executeUpdate("UPDATE Contrat SET prix=" + contrat.getPrix() + ", type='" + contrat.getType() + "' WHERE reference = " + contrat.getReference() );
		
		if (rsetModule >= 1) {
            isSuccess = true;
		}
		stmt.close();
		
		return isSuccess;
	}

}
