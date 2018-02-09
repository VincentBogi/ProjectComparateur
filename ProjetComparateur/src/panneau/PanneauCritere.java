package panneau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import bienImmobilier.Bien;
import bouton.MaJListe;
import bouton.MonBouton;
import bouton.MonJlabelFormulaire;
import bouton.MonTextFieldInt;
import comparateur.Comparateur;
import comparateur.Criteres;
import constante.ConstanteColor;
import constante.ConstanteVar;
import fenetre.MaFenetre;

public class PanneauCritere extends JPanel{
	MaFenetre maFenetre;
	
	// JLabel
	JLabel titre;
	JLabel apartementOuMaison;
	JLabel achatOuLocation;
	JLabel prixMin;
	JLabel prixMax;
	JLabel surfaceMin;
	JLabel surfaceMax;
	JLabel tn;
	JLabel localisation;
	JLabel pieceInterieur;
	JLabel parking;
	JLabel jardin;
	JLabel surfaceMinJardin;
	JLabel surfaceMaxJardin;
	JLabel nombreChambre;
	JLabel consoEnergie;
	
	// JComboBox, JFormattedTextField, JRadioButton
	JComboBox<String> apartementOuMaisonJL;
	JComboBox<String> achatOuLocationJL;
	JFormattedTextField prixMinJT;
	JFormattedTextField prixMaxJT;
	JFormattedTextField surfaceMinJT;
	JFormattedTextField surfaceMaxJT;
	JFormattedTextField tnJT;
	JFormattedTextField localisationJT;
	JFormattedTextField pieceInterieurJT;
	JComboBox<String> parkingJL;
	JRadioButton jardinOuiJRB;
	JRadioButton jardinNonJRB;
	JFormattedTextField surfaceMinJardinJT;
	JFormattedTextField surfaceMaxJardinJT;
	JFormattedTextField nombreChambreJT;
	JComboBox<String> consoEnergieJL;
	
	MonBouton boutonLancer;
	
	MonBouton boutonAjoutApparte; // pour casUtilisation 2
	MonBouton boutonAjoutMaison; // pour casUtilisation 2
	
	ButtonGroup bg;
	int casUtilisation; // 1 pour recherche 2 pour modif
	
	
	public PanneauCritere(MaFenetre maFenetre, int casUtilisation) {
		super();	
		setBackground(ConstanteColor.colorBackground);
		this.maFenetre = maFenetre;
		this.casUtilisation = casUtilisation;
		
		// format pour les JFormattedTextField
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l); //Optional
		
		// instencier les JLabel
		titre = new MonJlabelFormulaire("Remplissez les critères de recherches");
		apartementOuMaison = new MonJlabelFormulaire("Apartement ou Maison");
		achatOuLocation = new MonJlabelFormulaire("Achat ou Location");
		prixMin = new MonJlabelFormulaire("Prix Min");
		prixMax = new MonJlabelFormulaire("Prix Max");
		surfaceMin  = new MonJlabelFormulaire("Surface Min");
		surfaceMax = new MonJlabelFormulaire("Surface Max");
		tn = new MonJlabelFormulaire("Tn");
		localisation = new MonJlabelFormulaire("Code Postal");
		pieceInterieur = new MonJlabelFormulaire("Piece Interieur");
		parking = new MonJlabelFormulaire("Parking");
		jardin = new MonJlabelFormulaire("jardin");
		surfaceMinJardin = new MonJlabelFormulaire("Surface Min Jardin");
		surfaceMaxJardin = new MonJlabelFormulaire("Surface Max Jardin");
		nombreChambre = new MonJlabelFormulaire("Chambre");
		consoEnergie = new MonJlabelFormulaire("Consomation énergie");
		
		//instancier les champ de saisi et les liste
		apartementOuMaisonJL = new MaJListe(new String[]{ConstanteVar.bienTypeAppartement, ConstanteVar.bienTypeMaison });
		achatOuLocationJL = new MaJListe(new String[]{ConstanteVar.contratTypeVente, ConstanteVar.contratTypeLocation});
		prixMinJT = new MonTextFieldInt(numberFormatter);
		prixMaxJT = new MonTextFieldInt(numberFormatter);
		surfaceMinJT = new MonTextFieldInt(numberFormatter);
		surfaceMaxJT = new MonTextFieldInt(numberFormatter);
		tnJT = new MonTextFieldInt(numberFormatter);
		localisationJT = new MonTextFieldInt(numberFormatter);
		pieceInterieurJT = new MonTextFieldInt(numberFormatter);
		parkingJL = new MaJListe(new String[]{ConstanteVar.parkingPriver, ConstanteVar.parkingPublicSurveiller, ConstanteVar.parkingPublic, ConstanteVar.parkingAbsent  });
		bg = new ButtonGroup();
		jardinOuiJRB = new JRadioButton("Oui");
		jardinNonJRB = new JRadioButton("non", true);
		bg.add(jardinOuiJRB);
		bg.add(jardinNonJRB);
		surfaceMinJardinJT = new MonTextFieldInt(numberFormatter);
		surfaceMaxJardinJT = new MonTextFieldInt(numberFormatter);
		nombreChambreJT = new MonTextFieldInt(numberFormatter);	
		consoEnergieJL = new MaJListe(new String[]{ConstanteVar.consoEnergieA, ConstanteVar.consoEnergieB, ConstanteVar.consoEnergieC, ConstanteVar.consoEnergieD, ConstanteVar.consoEnergieE, ConstanteVar.consoEnergieF});
	
		//initilisation et instenciation du bouton de recherche
		boutonLancer = new MonBouton("Lancer Recherche");
		if(casUtilisation == 1) {	// recherche
			boutonLancer.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Criteres criteres = new Criteres();
					criteres.setSurfaceMax(Integer.parseInt(surfaceMaxJT.getValue().toString()));
					criteres.setSurfaceMin(Integer.parseInt(surfaceMinJT.getValue().toString()));
					criteres.setPrixMax(Integer.parseInt(prixMaxJT.getValue().toString()));
					criteres.setPrixMin(Integer.parseInt(prixMinJT.getValue().toString()));
					criteres.setTypeContrat(achatOuLocationJL.getSelectedItem().toString());
					criteres.setTypeBien(apartementOuMaisonJL.getSelectedItem().toString());
					criteres.setTypeTn(Integer.parseInt(tnJT.getValue().toString()));
					criteres.setConsoEnergie(consoEnergieJL.getSelectedItem().toString());
					if(jardinNonJRB.isSelected()) {
						criteres.setJardin(false, 0, 0);
					}
					else {
						criteres.setJardin(true, Integer.parseInt(surfaceMinJardinJT.getValue().toString()), Integer.parseInt(surfaceMaxJardinJT.getValue().toString()));
					}
					criteres.setLocalisation(Integer.parseInt(localisationJT.getValue().toString()));
					criteres.setNbChambre(Integer.parseInt(nombreChambreJT.getValue().toString()));
					criteres.setNbPieceInterieur(Integer.parseInt(pieceInterieurJT.getValue().toString()));
					criteres.setParcking(parkingJL.getSelectedItem().toString());
					
					
					maFenetre.setComparateur(new Comparateur(criteres));
					try {
						maFenetre.getComparateur().comparer();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//maFenetre.remove(maFenetre.getPanneauActif());
					PanneauListBien panneau = new PanneauListBien(maFenetre);
					panneau.initPanneauListBien();
					maFenetre.setPanneauActif(panneau);
					
					
				}
			});
		}
		else if(casUtilisation == 2) { // modif
			boutonLancer.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Criteres criteres = new Criteres();
					criteres.setSurfaceMax(Integer.parseInt(surfaceMaxJT.getValue().toString()));
					criteres.setSurfaceMin(Integer.parseInt(surfaceMinJT.getValue().toString()));
					criteres.setPrixMax(Integer.parseInt(prixMaxJT.getValue().toString()));
					criteres.setPrixMin(Integer.parseInt(prixMinJT.getValue().toString()));
					criteres.setTypeContrat(achatOuLocationJL.getSelectedItem().toString());
					criteres.setTypeBien(apartementOuMaisonJL.getSelectedItem().toString());
					criteres.setTypeTn(Integer.parseInt(tnJT.getValue().toString()));
					criteres.setConsoEnergie(consoEnergieJL.getSelectedItem().toString());
					if(jardinNonJRB.isSelected()) {
						criteres.setJardin(false, 0, 0);
					}
					else {
						criteres.setJardin(true, Integer.parseInt(surfaceMinJardinJT.getValue().toString()), Integer.parseInt(surfaceMaxJardinJT.getValue().toString()));
					}
					criteres.setLocalisation(Integer.parseInt(localisationJT.getValue().toString()));
					criteres.setNbChambre(Integer.parseInt(nombreChambreJT.getValue().toString()));
					criteres.setNbPieceInterieur(Integer.parseInt(pieceInterieurJT.getValue().toString()));
					criteres.setParcking(parkingJL.getSelectedItem().toString());
					
					
					maFenetre.setComparateur(new Comparateur(criteres));
					try {
						maFenetre.getComparateur().comparer();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//maFenetre.remove(maFenetre.getPanneauActif());
					PanneauListBienModif panneau = new PanneauListBienModif(maFenetre);
					panneau.initPanneauListBienModif();
					maFenetre.setPanneauActif(panneau);
					
					
				}
			});
		}
		
		jardinNonJRB.setBackground(ConstanteColor.colorBackground);
		jardinNonJRB.setForeground(ConstanteColor.colorFontText);
		jardinOuiJRB.setBackground(ConstanteColor.colorBackground);
		jardinOuiJRB.setForeground(ConstanteColor.colorFontText);
	}
	
	public void initPanneauCritere() {
		setLayout(new GridBagLayout ());
		GridBagConstraints c = new GridBagConstraints ();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		add(titre, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		add(apartementOuMaison, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		add(apartementOuMaisonJL, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 2;
		add(achatOuLocation, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 2;
		add(achatOuLocationJL, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 3;
		add(prixMin, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 3;
		add(prixMinJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 4;
		add(prixMax, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 4;
		add(prixMaxJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 5;
		add(surfaceMin, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 5;
		add(surfaceMinJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 6;
		add(surfaceMax, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 6;
		add(surfaceMaxJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 7;
		add(tn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 7;
		add(tnJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 8;
		add(localisation, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 8;
		add(localisationJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 9;
		add(pieceInterieur, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 9;
		add(pieceInterieurJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 10;
		add(parking, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 10;
		add(parkingJL, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 11;
		add(jardin, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 11;
		add(jardinOuiJRB, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 11;
		add(jardinNonJRB, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 12;
		add(surfaceMinJardin, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 12;
		add(surfaceMinJardinJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 13;
		add(surfaceMaxJardin, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 13;
		add(surfaceMaxJardinJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 14;
		add(nombreChambre, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 14;
		add(nombreChambreJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 15;
		add(consoEnergie, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 15;
		add(consoEnergieJL, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(20,0,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = GridBagConstraints.REMAINDER;
		add(boutonLancer, c);	
		
		if(casUtilisation == 2) {
			boutonAjoutMaison = new MonBouton("Ajouter Maison");
			boutonAjoutMaison.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PanneauAjoutBien panneau = new PanneauAjoutBien(maFenetre, 1);
					panneau.initPanneauAjoutBien();
					maFenetre.setPanneauActif(panneau);
					
				}
			});
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(20,0,0,0);
			c.weightx = 0;
			c.ipady = 20;
			c.ipadx = 60;
			c.weighty = 0;
			c.gridx = 0;
			c.gridy = 17;
			c.gridwidth = 1;
			add(boutonAjoutMaison, c);	
			
			boutonAjoutApparte = new MonBouton("Ajouter Appartement");
			boutonAjoutApparte.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					PanneauAjoutBien panneau = new PanneauAjoutBien(maFenetre, 2);
					panneau.initPanneauAjoutBien();
					maFenetre.setPanneauActif(panneau);
				}
			});
			
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(20,0,0,0);
			c.weightx = 0;
			c.ipady = 20;
			c.ipadx = 60;
			c.weighty = 0;
			c.gridx = 1;
			c.gridy = 17;
			c.gridwidth = 1;
			add(boutonAjoutApparte, c);	
			
			
		}
		
	}
	
	
}
