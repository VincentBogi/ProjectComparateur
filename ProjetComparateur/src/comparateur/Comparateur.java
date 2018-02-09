package comparateur;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bienImmobilier.Bien;
import bienImmobilier.Piece;
import constante.ConstanteVar;
import database.DAOBien;
import database.DAOContrat;
import database.DAOPiece;

public class Comparateur {
	private Criteres criteres;
	private List<Bien> biens;

	
	public Comparateur(Criteres criteres) {
		this.criteres = criteres;
		biens = new ArrayList<Bien>();
	}
	
	public void comparer() throws SQLException {
		DAOBien daoBien = DAOBien.getInstance();
		biens = daoBien.findByCriteres(criteres); // 1ere selection
		
		// 2ème étape de selection
		for(Bien bien : biens) {
			bien.evaluerValeurDeProximiter(criteres, ConstanteVar.hashMapLocalisation);
		}
		
		Collections.sort(biens, new Comparator<Bien>() {

			@Override
			public int compare(Bien arg0, Bien arg1) {
				int res = 0;
				if(arg0.getValeurDeProximite() < arg1.getValeurDeProximite()) {
					res = 1;
				}
				else if(arg0.getValeurDeProximite() > arg1.getValeurDeProximite()) {
					res = -1;
				}
				return res;
			}
		});
		
		//recuperation des 10 premiers si >
		if(biens.size() > 10) {
			biens = biens.subList(0, 10);
		}
		
		for(Bien bien : biens) {
			System.out.println(bien);
		}
	}

	public List<Bien> getBiens() {
		return biens;
	}

	public void setBiens(List<Bien> biens) {
		this.biens = biens;
	}
	
	public static int getOneContratRef() throws SQLException {
		DAOContrat daoContrat = DAOContrat.getInstance();
		int res = daoContrat.getNumContratDispo();
		return res;
		
	}
	
	public static int getOneNumBien() throws SQLException {
		DAOBien daoBien = DAOBien.getInstance();
		int res = daoBien.getNumBienDispo();
		return res;
		
	}
	
	public static void AddBienToBD(Bien bien) throws SQLException {
		DAOBien daoBien = DAOBien.getInstance();
		daoBien.insert(bien);
	}
	
	public static void deletePieceToBD(Piece piece) throws SQLException {
		DAOPiece daoPiece = DAOPiece.getInstance();
		daoPiece.delete(piece);
	}
	
	public static void deleteBienToBD(Bien bien) throws SQLException {
		DAOBien daoBien = DAOBien.getInstance();
		daoBien.delete(bien);
	}
	
	public static void updateBienToBD(Bien bien) throws SQLException {
		DAOBien daoBien = DAOBien.getInstance();
		daoBien.update(bien);
	}
	
	
}
