package panneau;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bienImmobilier.Bien;
import bouton.MonBouton;
import bouton.TextPresentationBien;
import comparateur.Comparateur;
import constante.ConstanteColor;
import fenetre.MaFenetre;
import panneau.PanneauListBien.ElementBien;

public class PanneauListBienModif extends JPanel{
	MaFenetre maFenetre;
	JPanel contentList;
	

	public PanneauListBienModif(MaFenetre maFenetre) {
		super(new BorderLayout());
		this.maFenetre = maFenetre;
		contentList = new JPanel(new GridBagLayout());
	}
	
	public void initPanneauListBienModif() {
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
        ElementBien elementBien;
        for(Bien bien : maFenetre.getComparateur().getBiens()) {
        	elementBien = new ElementBien(bien, contentList);
	        GridBagConstraints c = new GridBagConstraints();
	        
	        c.gridwidth = GridBagConstraints.HORIZONTAL;
	        c.insets = new Insets(10,0,0,0);
	        c.ipady = 50;
	        c.ipadx = 400;
	        c.weightx = 0;
	        c.weighty = i;
	        contentList.add(elementBien, c);
	        
	       
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        c.insets = new Insets(10,0,0,0);
	        c.ipady = 50;
	        c.ipadx = 30;
	        c.weightx = 1;
	        c.weighty = i;
	        contentList.add(elementBien.getMonBouton(), c);
	        
	        i++;
        }
	}
	
	public class ElementBien extends JPanel {
		JPanel contentList;
		Bien bien;
		TextPresentationBien text;
		MonBouton supprimmerBien;
		
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
			text = new TextPresentationBien("Bien numéro " + bien.getNumBien() + ", " + bien.getType() + " T" + bien.getTypeTn() +
					" de " + bien.getSurface()  + "m situé dans le " + bien.getLocalisation() + "\n"
					+ "Nombre de pieces interieurs : " + bien.getNbPiece() + "\n"
					+ "Nombre de chambre : " + bien.getNbChambre() + "\n"
					+ "Parcking : " + bien.getParking() + "\n"
					+ "Conso energie : " + bien.getConsoEnergie() + "\n" + str + "\n"
					+ "En " + bien.getContrat().getType() + " à " + bien.getContrat().getPrix() + "€\n\n"
					+ "Valeur Proximité : " + bien.getValeurDeProximite());
			
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
					PanneauChangeBien panneau = new PanneauChangeBien(maFenetre, bien);
					panneau.initPanneauChangeBien();
					maFenetre.setPanneauActif(panneau);
					
				}
			});
			
			supprimmerBien = new MonBouton("Supprimer");
			supprimmerBien.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					maFenetre.getComparateur().getBiens().remove(bien);
					
					try {
						Comparateur.deleteBienToBD(bien);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					PanneauListBienModif panneau = new PanneauListBienModif(maFenetre);
					panneau.initPanneauListBienModif();
					maFenetre.setPanneauActif(panneau);
					
				}
			});
			
			add(text);
		}
		
		public MonBouton getMonBouton() {
			return supprimmerBien;
		}
	}
}
