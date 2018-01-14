package database;

import java.sql.SQLException;
import java.util.List;

import bienImmobilier.Piece;

public interface DAOPieceInterface {
	public List<Piece> findByNumBien(int numPiece) throws SQLException;
    public boolean insert(Piece piece, int numBien) throws SQLException;
    public boolean delete(Piece piece) throws SQLException;
    public boolean deleteByNumBien(int numBien) throws SQLException;
    public boolean update(Piece piece) throws SQLException;
}
