package bouton;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import constante.ConstanteColor;

public class TextPresentationBien extends JTextArea{
	public TextPresentationBien(String str) {
		super(str);
		setForeground(ConstanteColor.colorTextOnElement);
		Font font = new Font("Arial",Font.BOLD,18);
		setFont(font);
		setEditable(false); 
		setBackground(ConstanteColor.colorElement);
		setMargin(new Insets(10,10,10,10));
	}
}
