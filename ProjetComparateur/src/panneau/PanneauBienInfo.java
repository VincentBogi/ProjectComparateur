package panneau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import bienImmobilier.Bien;
import bienImmobilier.Piece;
import bouton.MonBouton;
import bouton.MonJlabelFormulaire;
import bouton.TextPresentationBien;
import constante.ConstanteColor;
import fenetre.MaFenetre;

public class PanneauBienInfo extends JPanel {
	MaFenetre maFenetre;
	Bien bien;
	MonBouton backBoutton;
	JPanel gauche;
	JPanel droite;
	TextPresentationBien text1;
	TextPresentationBien text2;
	MonJlabelFormulaire titre;
	

	public PanneauBienInfo(MaFenetre maFenetre, Bien bien) {
		super(new GridLayout(1,2));
		setBackground(ConstanteColor.colorBackground);
		
		this.bien = bien;
		this.maFenetre = maFenetre;
		
		gauche = new JPanel(new GridBagLayout());
		gauche.setBackground(ConstanteColor.colorBackground);
		droite = new JPanel(new GridBagLayout());
		droite.setBackground(ConstanteColor.colorBackground);
		
		titre = new MonJlabelFormulaire("Liste de toutes les pièces de mon bien ainsi que leur surface associé");
		Font font = new Font("Arial",Font.BOLD,22);
		titre.setFont(font);
		
		String str = "";
		if(bien.isJardin()) {
			str = "Jardin de " + bien.getSurfaceJardin();
		}
		text1 = new TextPresentationBien("Bien numéro " + bien.getNumBien() + ", " + bien.getType() + " T" + bien.getTypeTn() +
				" de " + bien.getSurface()  + "m situé dans le " + bien.getLocalisation() + "\n"
				+ "Nombre de pieces interieurs : " + bien.getNbPiece() + "\n"
				+ "Nombre de chambre : " + bien.getNbChambre() + "\n"
				+ "Parcking : " + bien.getParking() + "\n"
				+ "Conso energie : " + bien.getConsoEnergie() + "\n" + str + "\n"
				+ "En " + bien.getContrat().getType() + " à " + bien.getContrat().getPrix() + "€\n\n"
				+ "Valeur Proximité : " + bien.getValeurDeProximite());
		
		text2 = new TextPresentationBien(bien.getText());
		
		backBoutton = new MonBouton("Retour");
		backBoutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PanneauListBien panneau = new PanneauListBien(maFenetre);
				panneau.initPanneauListBien();
				maFenetre.setPanneauActif(panneau);
			}
		});
	}
	
	public void initPanneauBienInfo() {
		add(gauche);
		add(droite);
		
		//panneau gauche
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		gauche.add(backBoutton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(100,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		gauche.add(text1, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(60,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		gauche.add(text2, c);
		
		
		//panneau droite
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		droite.add(titre, c);
		
		MonJlabelFormulaire jlabel;
		int i = 1; 
		for(Piece piece : bien.getPiece()) {
			jlabel = new MonJlabelFormulaire("	- " + piece.getFonction() + " de " + piece.getSurface() + "m");
			Font font = new Font("Arial",Font.BOLD,18);
			jlabel.setFont(font);
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(5,0,0,0);
			c.weightx = 0;
			c.ipady = 10;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = i;
			droite.add(jlabel, c);
			
			i++;
		}
		
	}
}
