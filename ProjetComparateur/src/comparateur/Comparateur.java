package comparateur;

import java.util.ArrayList;
import java.util.List;

import bienImmobilier.Bien;

public class Comparateur {
	private Criteres criteres;
	private List<Bien> biens;
	
	public Comparateur(Criteres criteres) {
		this.criteres = criteres;
		biens = new ArrayList<Bien>();
	}
}
