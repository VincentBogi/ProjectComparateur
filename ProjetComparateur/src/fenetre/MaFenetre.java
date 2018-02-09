package fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import comparateur.Comparateur;
import panneau.BarreMenu;
import panneau.PanneauCritere;

public class MaFenetre extends JFrame {
	BarreMenu barreMenu;
	JPanel panneauActif;
	PanneauCritere panneauCritere;
	Comparateur comparateur;
	
	public MaFenetre() {
		super();
		barreMenu = new BarreMenu(this);
		panneauCritere = new PanneauCritere(this, 1);
		setTitle("ImmoComp");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initFenetre() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		// Dimention de la fenetre
		int largeur = (dim.width / 3) * 2;
		int hauteur = (dim.height / 3) * 3;
		Dimension dimFenetre = new Dimension(largeur, hauteur); 
		setSize(new Dimension(dim.width, dim.height));

		
		panneauActif = panneauCritere;
		
		//taille barreMenu
		barreMenu.initBarreMenu(new Dimension((dim.width / 3) * 2, 80));
		panneauCritere.initPanneauCritere();
		
		// ajoute élément 
		this.getContentPane().add(barreMenu, BorderLayout.NORTH);
		this.getContentPane().add(panneauActif, BorderLayout.CENTER);
		this.pack();
		this.setExtendedState(this.MAXIMIZED_BOTH);
	}

	public JPanel getPanneauActif() {
		return panneauActif;
	}
	
	public void setPanneauActif(JPanel jpanel) {
		remove(panneauActif);
		panneauActif = jpanel;
		add(jpanel, BorderLayout.CENTER);
		revalidate();
		repaint(); 
		pack();
		this.setExtendedState(this.MAXIMIZED_BOTH);
	}

	public PanneauCritere getPanneauCritere() {
		return panneauCritere;
	}
	
	public BarreMenu getBarreMenu() {
		return barreMenu;
	}

	public Comparateur getComparateur() {
		return comparateur;
	}

	public void setComparateur(Comparateur comparateur) {
		this.comparateur = comparateur;
	}
	
	

}
