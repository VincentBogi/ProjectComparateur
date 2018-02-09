package panneau;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import bouton.MonBoutonMenu;
import constante.ConstanteColor;
import constante.ConstanteVar;
import fenetre.MaFenetre;

public class BarreMenu extends JPanel{
	MaFenetre maFenetre;
	JPanel paneGauche;
	JPanel paneDroite;
	MonBoutonMenu connexion;
	MonBoutonMenu recherche;
	MonBoutonMenu modification;
	
	public BarreMenu(MaFenetre maFenetre) {
		super();
		setBackground(ConstanteColor.colorBarreMenu);
		this.maFenetre = maFenetre;
		
		setLayout (new GridBagLayout ());
		GridBagConstraints c = new GridBagConstraints ();
		
		paneGauche = new JPanel();
		paneGauche.setBackground(ConstanteColor.colorBarreMenu);
		
		paneDroite = new JPanel();
		paneDroite.setBackground(ConstanteColor.colorBarreMenu);
		
		//bouton
		recherche = new MonBoutonMenu("Recherche");
		modification = new MonBoutonMenu("Modification");
		connexion = new MonBoutonMenu("Connexion");
		
		recherche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PanneauCritere panneau = new PanneauCritere(maFenetre, 1);
				panneau.initPanneauCritere();
				maFenetre.setPanneauActif(panneau);
				
			}
		});
		
		modification.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PanneauCritere panneau = new PanneauCritere(maFenetre, 2);
				panneau.initPanneauCritere();
				maFenetre.setPanneauActif(panneau);
				
			}
		});
	}
	
	public void initBarreMenu(Dimension dim) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setPreferredSize(dim);
		
		
		setLayout(new GridBagLayout ());
		GridBagConstraints c = new GridBagConstraints ();
		
		// init GridBagContraints pour paneau gauche et droit
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0.8;
		c.ipady = 80;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		add (paneGauche, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0.2;
		c.gridx = 1;
		c.gridy = 0;
		add (paneDroite, c);
		
		paneGauche.setLayout(new FlowLayout(FlowLayout.LEFT));
		paneDroite.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		
		//init dim bouton
		int x = 200;
		int y = dim.height;
		
		recherche.setPreferredSize(new Dimension(x, y));
		recherche.setBorderPainted(true);
		modification.setPreferredSize(new Dimension(x, y));
		modification.setBorderPainted(true);
		connexion.setPreferredSize(new Dimension(x, y));
		connexion.setBorderPainted(true);
		
		paneGauche.add(recherche);
		paneGauche.add(modification);
		paneDroite.add(connexion);
		
	}
	
	
	
}
