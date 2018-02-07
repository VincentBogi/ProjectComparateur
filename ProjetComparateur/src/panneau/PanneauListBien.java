package panneau;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bienImmobilier.Bien;
import fenetre.MaFenetre;

public class PanneauListBien extends JPanel {
	MaFenetre maFenetre;
	JPanel contentList;
	

	public PanneauListBien(MaFenetre maFenetre) {
		super();
		this.maFenetre = maFenetre;
		
		contentList = new JPanel(new GridBagLayout());

	}
	
	public void initPanneauListBien() {
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(maFenetre.getWidth(), maFenetre.getHeight() - maFenetre.getBarreMenu().getHeight()));
		
		contentList.setBackground(Color.yellow);
        add(contentList, BorderLayout.CENTER);
        
        
        add(new JScrollPane(contentList));
	}
	
	public class ElementBien extends JPanel {
		JPanel contentList;
		Bien bien;
		PanneauListBien contentListBien;
		
		public ElementBien(Bien bien, PanneauListBien contentListBien) {
			super();
			this.bien = bien;
		}
	}
}
