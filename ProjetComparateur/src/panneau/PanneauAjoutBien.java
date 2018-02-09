package panneau;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.NumberFormatter;

import bienImmobilier.Appartement;
import bienImmobilier.Bien;
import bienImmobilier.Contrat;
import bienImmobilier.FabriquePiece;
import bienImmobilier.Location;
import bienImmobilier.Maison;
import bienImmobilier.Piece;
import bienImmobilier.Vente;
import bouton.MaJListe;
import bouton.MonBouton;
import bouton.MonJlabelFormulaire;
import bouton.MonTextFieldInt;
import bouton.TextPresentationBien;
import comparateur.Comparateur;
import constante.ConstanteColor;
import constante.ConstanteVar;
import fenetre.MaFenetre;

public class PanneauAjoutBien extends JPanel {
	MaFenetre maFenetre;
	Bien bien;
	List<Piece> listPieces;
	JPanel gauche;
	JPanel droite;
	MonJlabelFormulaire titre;
	
	/***panneau gauche ***/
	MonJlabelFormulaire tn;
	MonJlabelFormulaire surface;
	MonJlabelFormulaire prix;
	MonJlabelFormulaire localisation;
	MonJlabelFormulaire parking;
	MonJlabelFormulaire energie;
	MonJlabelFormulaire text;
	MonJlabelFormulaire achatOuLocation;
	
	JComboBox<String> achatOuLocationJL;
	JFormattedTextField tnJT;
	JFormattedTextField surfaceJT;
	JFormattedTextField prixJT;
	JFormattedTextField localisationJT;
	JComboBox<String> parkingJL;
	JComboBox<String> energieJL;
	JTextArea textJT;
	
	MonBouton ajouterBien;
	
	
	/***panneau droite ***/
	MonBouton ajouterPiece;
	
	MonJlabelFormulaire titre2;
	MonJlabelFormulaire pieces;
	
	JComboBox<String> piecesJL;
	JFormattedTextField surfacePieceJT;
	
	int cmpDroite = 2;
	
	
	
	
	int casUtilisation; //1 pour maison 2 pour appartement 

	public PanneauAjoutBien(MaFenetre maFenetre, int casUtilisation) {
		super(new GridLayout(1,2));
		setBackground(ConstanteColor.colorBackground);

		listPieces = new ArrayList<>();
		this.maFenetre = maFenetre;
		this.casUtilisation = casUtilisation;
		
		if(casUtilisation == 1) {
			bien = new Maison(0);
		}
		else if(casUtilisation == 2) {
			bien = new Appartement(0);
		}
		
		// format pour les JFormattedTextField
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); //this is the key!!
		numberFormatter.setMinimum(0l); //Optional
		
		/*** page gauche ***/
		titre = new MonJlabelFormulaire("Ajout d'un bien immobilier dans la base de donné");
		achatOuLocation = new MonJlabelFormulaire("Achat ou location");
		tn = new MonJlabelFormulaire("TN");
		surface = new MonJlabelFormulaire("Surface");
		prix = new MonJlabelFormulaire("Prix");
		localisation = new MonJlabelFormulaire("Localisation");
		parking = new MonJlabelFormulaire("Parking");
		energie = new MonJlabelFormulaire("energie");
		text = new MonJlabelFormulaire("Text descriptif");
		
		achatOuLocationJL = new MaJListe(new String[]{ConstanteVar.contratTypeVente, ConstanteVar.contratTypeLocation});
		tnJT = new MonTextFieldInt(numberFormatter);
		tnJT.setValue(0);
		surfaceJT = new MonTextFieldInt(numberFormatter);
		surfaceJT.setValue(0);
		prixJT = new MonTextFieldInt(numberFormatter);
		prixJT.setValue(0);
		localisationJT = new MonTextFieldInt(numberFormatter);
		localisationJT.setValue(0);
		parkingJL = new MaJListe(new String[]{ConstanteVar.parkingPriver, ConstanteVar.parkingPublicSurveiller, ConstanteVar.parkingPublic, ConstanteVar.parkingAbsent  });
		energieJL = new MaJListe(new String[]{ConstanteVar.consoEnergieA, ConstanteVar.consoEnergieB, ConstanteVar.consoEnergieC, ConstanteVar.consoEnergieD, ConstanteVar.consoEnergieE, ConstanteVar.consoEnergieF});
		textJT = new JTextArea();
		textJT.setText("");
		
		ajouterBien = new MonBouton("Ajouter Bien");
		ajouterBien.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					buildBienAndAddToData();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		/*** page droite ***/
		titre2 = new MonJlabelFormulaire("Ajout des pieces associés au bien immobilier");
		pieces = new MonJlabelFormulaire("Surface Piece et type");
		piecesJL = new MaJListe(new String[] {ConstanteVar.pieceFonctionChambre, ConstanteVar.pieceFonctionCuisine, ConstanteVar.pieceFonctionGarage, ConstanteVar.pieceFonctionJardin, ConstanteVar.pieceFonctionSaleDeBain, ConstanteVar.pieceFonctionSalon, ConstanteVar.pieceFonctionToilette});
		surfacePieceJT = new JFormattedTextField(numberFormatter);
		surfacePieceJT.setValue(0);
		
		gauche = new JPanel(new GridBagLayout());
		gauche.setBackground(ConstanteColor.colorBackground);
		droite = new JPanel(new GridBagLayout());
		droite.setBackground(ConstanteColor.colorBackground);
		
		ajouterPiece = new MonBouton("Ajouter Piece");
		ajouterPiece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FabriquePiece fabriquePiece = new FabriquePiece();
				Piece p = fabriquePiece.creerPiece(piecesJL.getSelectedItem().toString(), Integer.parseInt(surfacePieceJT.getValue().toString()), bien);
				listPieces.add(p);
				pieceBuildPiece(p);
				
				droite.repaint();
				maFenetre.pack();
				maFenetre.setExtendedState(maFenetre.MAXIMIZED_BOTH);
				
			}
		});
		
	}
	
	public void initPanneauAjoutBien() {
		add(gauche);
		add(droite);
		initPanneauGauche();
		initPanneauDroite();
	}
	
	public void initPanneauGauche() {
		//panneau gauche
				GridBagConstraints c = new GridBagConstraints();	
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 10;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 0;
				gauche.add(achatOuLocation, c);
				
				//panneau gauche
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 10;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 1;
				gauche.add(achatOuLocation, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 10;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 1;
				gauche.add(achatOuLocationJL, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 10;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 2;
				gauche.add(tn, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 10;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 2;
				gauche.add(tnJT, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 3;
				gauche.add(surface, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 3;
				gauche.add(surfaceJT, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 4;
				gauche.add(prix, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 4;
				gauche.add(prixJT, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 5;
				gauche.add(localisation, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 5;
				gauche.add(localisationJT, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 6;
				gauche.add(parking, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 6;
				gauche.add(parkingJL, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 7;
				gauche.add(energie, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 7;
				gauche.add(energieJL, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 8;
				gauche.add(text, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(0,20,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 1;
				c.gridy = 8;
				gauche.add(textJT, c);
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.insets = new Insets(40,0,0,0);
				c.weightx = 0;
				c.ipady = 20;
				c.weighty = 0;
				c.gridx = 0;
				c.gridy = 9;
				c.gridwidth = GridBagConstraints.REMAINDER;
				gauche.add(ajouterBien, c);
	}
	
	public void initPanneauDroite() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 0;
		droite.add(titre2, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = 1;
		droite.add(pieces, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.ipadx = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = 1;
		droite.add(surfacePieceJT, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 2;
		c.gridy = 1;
		droite.add(piecesJL, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 20;
		c.weighty = 0;
		c.gridx = 3;
		c.gridy = 1;
		droite.add(ajouterPiece, c);
		
		List<Piece> listeTmp = new ArrayList<>();
		
		// clone
		for(Piece p : listPieces) {
			listeTmp.add(p);
		}
		
		
		//construction et ajout des élément dans panneau droit
		for(Piece p : listeTmp) {
			if(p != null) {
				pieceBuildPiece(p);
			}
		}
		
		droite.repaint();
		maFenetre.pack();
		maFenetre.setExtendedState(maFenetre.MAXIMIZED_BOTH);
	}
	
	public void pieceBuildPiece(Piece piece) {
		PieceJlabel pj = new PieceJlabel(piece);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 10;
		c.ipadx = 20;
		c.weighty = 0;
		c.gridx = 0;
		c.gridy = cmpDroite;
		droite.add(pj, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,20,0,0);
		c.weightx = 0;
		c.ipady = 10;
		c.ipadx = 20;
		c.weighty = 0;
		c.gridx = 1;
		c.gridy = cmpDroite;
		droite.add(pj.getMonBouton(), c);
		
		cmpDroite++;
	}
	
	public void buildBienAndAddToData() throws SQLException {
		int reference = Comparateur.getOneContratRef();
		Contrat c;
		if(piecesJL.getSelectedItem().toString().equals(ConstanteVar.contratTypeLocation)) {
			c = new Location(reference, Integer.parseInt(prixJT.getValue().toString()));
		}
		else {
			c = new Vente(reference, Integer.parseInt(prixJT.getValue().toString()));
		}
		
		bien.setContrat(c);
		bien.setPiece(listPieces);
		bien.setConsoEnergie(energieJL.getSelectedItem().toString());
		bien.setLocalisation(Integer.parseInt(localisationJT.getValue().toString()));
		bien.setParking(parkingJL.getSelectedItem().toString());
		bien.setTypeTn(Integer.parseInt(tnJT.getValue().toString()));
		bien.setSurface(Integer.parseInt(surfaceJT.getValue().toString()));
		bien.setText(textJT.getText());
		
		bien.setNumBien(Comparateur.getOneNumBien());
		
		Comparateur.AddBienToBD(bien);
	}
	
	public class PieceJlabel extends MonJlabelFormulaire {
		Piece piece;
		MonBouton deletePiece;
		MonJlabelFormulaire element;
		
		public PieceJlabel(Piece piece) {
			super(piece.getFonction() + " de surface " + piece.getSurface() + "m");
			this.piece = piece;
			element = this;
			deletePiece = new MonBouton("supp");
			deletePiece.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					listPieces.remove(piece);
					
					cmpDroite = 2;
					
					droite.removeAll();
					initPanneauDroite();
					droite.repaint();
					maFenetre.pack();
					maFenetre.setExtendedState(maFenetre.MAXIMIZED_BOTH);
				}
			});
		}
		
		public MonBouton getMonBouton() {
			return deletePiece;
		}
	}
}
