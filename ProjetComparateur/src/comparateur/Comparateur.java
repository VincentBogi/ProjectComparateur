package comparateur;

import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Bien;

public class Comparateur {
	Criteres critere;
	List<Bien> biens;
	
	public Comparateur(Criteres criteres) {
		this.critere = criteres;
		biens = new ArrayList<Bien>();
	}
}
