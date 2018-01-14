package database;

import java.sql.SQLException;
import java.util.List;

import bienImmobilier.Contrat;

public interface DAOContratInterface {
	public Contrat findByReference(int reference) throws SQLException;
    public boolean insert(Contrat contrat) throws SQLException;
    public boolean delete(Contrat contrat) throws SQLException;
    public boolean update(Contrat contrat) throws SQLException;
}
