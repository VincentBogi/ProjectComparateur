package panneau;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bienImmobilier.Bien;
import bouton.MonJlabelFormulaire;
import bouton.TextPresentationBien;
import constante.ConstanteColor;
import fenetre.MaFenetre;

public class PanneauListBien extends JPanel {
	MaFenetre maFenetre;
	JPanel contentList;
	

	public PanneauListBien(MaFenetre maFenetre) {
		super(new BorderLayout());
		this.maFenetre = maFenetre;
		contentList = new JPanel(new GridBagLayout());
	}
	
	public void initPanneauListBien() {
		setBackground(ConstanteColor.colorBackground);
		setPreferredSize(new Dimension(maFenetre.getWidth(), maFenetre.getHeight() - maFenetre.getBarreMenu().getHeight()));
		
		//scroll
		JScrollPane jc = new JScrollPane(contentList);
		jc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jc.setPreferredSize(new Dimension(20, 500));
		
		contentList.setBackground(ConstanteColor.colorBackground);
        
        add(jc, BorderLayout.CENTER);
        
        //creation des element de ma liste
        int i = 1;
        for(Bien bien : maFenetre.getComparateur().getBiens()) {
	        GridBagConstraints c = new GridBagConstraints();
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        c.insets = new Insets(10,0,0,0);
	        c.ipady = 50;
	        c.ipadx = 400;
	        c.weightx = 0;
	        c.weighty = i;
	        contentList.add(new ElementBien(bien, contentList), c);
	        i++;
        }
	}
	
	public class ElementBien extends JPanel {
		JPanel contentList;
		Bien bien;
		TextPresentationBien text;
		
		public ElementBien(Bien bien, JPanel contentListBien) {
			super();
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			setLayout(boxLayout);
			this.bien = bien;
			this.contentList = contentListBien;
			setBackground(ConstanteColor.colorElement);
			setPreferredSize(new Dimension(400, 170));
			
			String str = "";
			if(bien.isJardin()) {
				str = "Jardin de " + bien.getSurfaceJardin();
			}
			text = new TextPresentationBien("Bien num�ro " + bien.getNumBien() + ", " + bien.getType() + " T" + bien.getTypeTn() +
					" de " + bien.getSurface()  + "m situ� dans le " + bien.getLocalisation() + "\n"
					+ "Nombre de pieces interieurs : " + bien.getNbPiece() + "\n"
					+ "Nombre de chambre : " + bien.getNbChambre() + "\n"
					+ "Parcking : " + bien.getParking() + "\n"
					+ "Conso energie : " + bien.getConsoEnergie() + "\n" + str + "\n"
					+ "En " + bien.getContrat().getType() + " � " + bien.getContrat().getPrix() + "�\n\n"
					+ "Valeur Proximit� : " + bien.getValeurDeProximite());
			
			text.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					PanneauBienInfo panneau = new PanneauBienInfo(maFenetre, bien);
					panneau.initPanneauBienInfo();
					maFenetre.setPanneauActif(panneau);
					
				}
			});
			
			add(text);
		}
	}
}
