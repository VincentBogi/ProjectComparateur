package database;

import java.sql.SQLException;
import java.util.List;

import bienImmobilier.Bien;
import comparateur.Criteres;


public interface DAOBienInterface {
	public List<Bien> findByCriteres(Criteres criteres) throws SQLException;
    public boolean insert(Bien bien) throws SQLException;
    public boolean delete(Bien bien) throws SQLException;
    public boolean update(Bien bien) throws SQLException;
}
