package bouton;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import constante.ConstanteColor;

public class MonBouton extends JButton{
	public MonBouton(String nom) {
		super(nom);
		setBackground(ConstanteColor.colorBouton);
		setForeground(ConstanteColor.colorFontTextButon);
		
		setFocusPainted(false); 
		
	}
}
