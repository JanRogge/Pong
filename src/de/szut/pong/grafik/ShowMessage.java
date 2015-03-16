package de.szut.pong.grafik;

import javax.swing.JOptionPane;

/**
 * Erzeugt die Anzeigefenter
 * @author Jan
 */
public class ShowMessage extends JOptionPane{

	private static final long serialVersionUID = -510567573915317942L;
	private String playerstr;

	public ShowMessage(){
		setVisible(true);
	}
	/**
	 * Methode zum Erstellen der Errormeldung
	 */
	public void Error(String error){
		showMessageDialog(null, error, "Error", JOptionPane.WARNING_MESSAGE);
	}
	/**
	 * Methode zum Anzeigen welcher spieler gewonnen hat
	 */
	public void Victory(int player){
		if (player == 1){
			playerstr = "Left";
		}
		else{
			playerstr = "Right";
		}
		showMessageDialog(null, playerstr + " Player wins", "Victory", JOptionPane.PLAIN_MESSAGE);
	}
	
	/**
	 * Methode zum Anzeigen der Halbzeit
	 */
	public void Break(){
		showMessageDialog(null, "Halbzeit", "Halbzeit", JOptionPane.PLAIN_MESSAGE);
	}
}

