package bouton;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import constante.ConstanteColor;

public class MonBoutonMenu extends JButton{
	public MonBoutonMenu(String nom) {
		super(nom);
		setBackground(ConstanteColor.colorBarreMenu);
		setForeground(ConstanteColor.colorFontMenu);
		
		setFocusPainted(false); 
		setMargin(null);            
		setBorder(BorderFactory.createEmptyBorder()); 
		setContentAreaFilled(true);
	}
}
