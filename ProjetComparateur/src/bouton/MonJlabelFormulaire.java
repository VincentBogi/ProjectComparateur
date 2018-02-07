package bouton;

import javax.swing.JLabel;

import constante.ConstanteColor;

public class MonJlabelFormulaire extends JLabel{
	public MonJlabelFormulaire(String str ) {
		super(str);
		setForeground(ConstanteColor.colorFontText);
	}
}
