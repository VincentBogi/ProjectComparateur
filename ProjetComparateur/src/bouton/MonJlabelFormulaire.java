package bouton;

import java.awt.Font;

import javax.swing.JLabel;

import constante.ConstanteColor;

public class MonJlabelFormulaire extends JLabel{
	public MonJlabelFormulaire(String str ) {
		super(str);
		setForeground(ConstanteColor.colorFontText);
		Font font = new Font("Arial",Font.PLAIN,15);
		setFont(font);
	}
}
