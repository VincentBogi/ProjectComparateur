package constante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConstanteVar {
	// BD valeur pour contrat
	public static final String contratTypeVente = "vente";
	public static final String contratTypeLocation = "location";
	
	// BD valeur pour Piece
	public static final String pieceTypeExterieur = "exterieur";
	public static final String pieceTypeInterieur = "interieur";
	public static final String pieceFonctionChambre = "chambre";
	public static final String pieceFonctionToilette = "toilette";
	public static final String pieceFonctionCuisine = "cuisine";
	public static final String pieceFonctionSaleDeBain = "salle de bain";
	public static final String pieceFonctionSalon = "salon";
	public static final String pieceFonctionBalcon = "balcon";
	public static final String pieceFonctionGarage = "garage";
	public static final String pieceFonctionJardin = "jardin";
	
	// BD valeur pour Piece
	public static final String bienTypeAppartement = "appartement";
	public static final String bienTypeMaison = "maison";
	
	// BD valeur énergie
	public static final String consoEnergieA = "A";
	public static final String consoEnergieB = "B";
	public static final String consoEnergieC = "C";
	public static final String consoEnergieD = "D";
	public static final String consoEnergieE = "E";
	public static final String consoEnergieF = "F";
	
	// BD valeur parking
	public static final String parkingPriver = "priver";
	public static final String parkingPublicSurveiller = "public surveiller";
	public static final String parkingPublic = "public";
	public static final String parkingAbsent = "pas de place";
	
	// hmap pour consoEnergie
	public static final HashMap<String, Integer> hashMapConso;
	static {
			HashMap<String,Integer> tmp = new HashMap<String,Integer>();
	        tmp.put(consoEnergieA,1);
	        tmp.put(consoEnergieB,2);
	        tmp.put(consoEnergieC,3);
	        tmp.put(consoEnergieD,4);
	        tmp.put(consoEnergieE,5);
	        tmp.put(consoEnergieF,6);
	        
	        hashMapConso = tmp;
	}
	
	// hmap pour le parking
	public static final HashMap<String, Integer> hashMapParking;
	static {
		// erreur probable avec tmp
		HashMap<String,Integer> tmp = new HashMap<String,Integer>();
		tmp.put(parkingPriver, 1);
		tmp.put(parkingPublicSurveiller, 2);
		tmp.put(parkingPublic, 3);
		tmp.put(parkingAbsent, 4);
		
        hashMapParking = tmp;
	}
	
	// hmap pour la location 
	public static final HashMap<Integer,List<Integer>> hashMapLocalisation;
	static {
		HashMap<Integer, List<Integer>> tmp = new HashMap<Integer, List<Integer>>();
		
		// 1er arrondissement
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(13002);
		list.add(13003);
		list.add(13004);
		list.add(13005);
		list.add(13006);
		list.add(13007);
		tmp.put(13001, list);
		
		// 2eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13003);
		list.add(13015);
		tmp.put(13002, list);
		
		// 3eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13002);
		list.add(13004);
		list.add(13014);
		list.add(13015);
		tmp.put(13003, list);
		
		// 4eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13003);
		list.add(13005);
		list.add(13012);
		list.add(13013);
		list.add(13014);
		tmp.put(13004, list);
		
		// 5eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13004);
		list.add(13006);
		list.add(13010);
		list.add(13012);
		tmp.put(13005, list);
		
		// 6eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13005);
		list.add(13007);
		list.add(13008);
		list.add(13010);
		tmp.put(13006, list);
		
		// 7eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13001);
		list.add(13006);
		list.add(13008);
		tmp.put(13007, list);
		
		// 8eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13006);
		list.add(13007);
		list.add(13009);
		list.add(13010);
		tmp.put(13008, list);
		
		// 9eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13008);
		list.add(13010);
		list.add(13011);
		tmp.put(13009, list);
		
		// 10eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13005);
		list.add(13006);
		list.add(13008);
		list.add(13009);
		list.add(13011);
		list.add(13012);
		tmp.put(13010, list);
		
		// 11eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13009);
		list.add(13010);
		list.add(13012);
		tmp.put(13011, list);
		
		// 12eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13004);
		list.add(13005);
		list.add(13010);
		list.add(13011);
		list.add(13013);
		tmp.put(13012, list);
		
		// 13eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13003);
		list.add(13004);
		list.add(13012);
		list.add(13014);
		tmp.put(13013, list);
		
		// 14eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13003);
		list.add(13004);
		list.add(13013);
		list.add(13015);
		tmp.put(13014, list);
		
		// 15eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13002);
		list.add(13003);
		list.add(13014);
		list.add(13016);
		tmp.put(13015, list);
		
		// 16eme arrondissement
		list = new ArrayList<Integer>();
		list.add(13015);
		tmp.put(13016, list);
		
		hashMapLocalisation = tmp;
	}
	
	
	
	
}
