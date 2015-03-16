package de.szut.pong.grafik;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Das Panel
 * @author Jan
 */
public class GamePanel extends JPanel{
	
	private static final long serialVersionUID = 35503710248370486L;
	private JLabel fieldLine;
	private JLabel fieldBall;
	private JLabel fieldPad1= new JLabel("");
	private JLabel fieldPad2= new JLabel("");
	public final static int SIZE = 10;
	public static final int HEIGHT = 60;
	public static final int WIDTH = 65;
	private Points points1;
	private Points points2;
	private ShowMessage messanger;
	
	/**
	 * Konstruktor
	 */
	public GamePanel() {
		messanger = new ShowMessage();
		setBackground(Color.white); //Setzt den hintergrund des Frames auf Weiß
		setSize(SIZE * WIDTH + 16, SIZE * HEIGHT + 39); //Setzt die Größe relative zur größe einer Zelle
		setLayout(null); //Absolut layout
		
				
		//Schläger player1-2 y0-54
		Pad(1, (HEIGHT - 6)/2, fieldPad1); //erzeugt einen neuen schläger für Spieler 1
		Pad(2, (HEIGHT - 6)/2, fieldPad2); //erzeugt einen neuen schläger für Spieler 2
		
		//Ball x0-64 y0-59
		Ball(1, (HEIGHT-1)/2);  //erzeugt einen neuen Ball
		
		/***
		 * Erzeugt eine Feldmittelline in der mitte des Feldes
		 ***/
		fieldLine= new JLabel();
		fieldLine.setLocation((int) (SIZE * ((WIDTH-1.0)/2)), 0);
		fieldLine.setSize(SIZE, SIZE * HEIGHT);
		fieldLine.setBackground(Color.green);
		fieldLine.setVisible(true);
		fieldLine.setOpaque(true);
		add(fieldLine);
		
		points1 = new Points(1, this); //erzeugt einen neue Punkteanzeige für Spieler 1
		points2 = new Points(2, this); //erzeugt einen neue Punkteanzeige für Spieler 2
	}
	
	/**
	 * Methode zum setzten der Punkteanzeige für die jeweilige punkte zahl#
	 */
	public void setPoints(int player, int point) {
		if (player == 1) {
			points1.setPoints(player, point);
		}
		else {
			points2.setPoints(player, point);
		}
	}
	
	/**
	 * Methode zum bewegen des Schlägers und des Balls in einem Zug
	 */
	public void draw(int playerPos1, int playerPos2, int ballX, int ballY) {
		movePad(1, playerPos1);
		movePad(2, playerPos2);
		moveBall(ballX, ballY);
	}
	
	/**
	 * Methode zum Erzeugen des Balls mit der vorgegebenen Zellen größe
	 */
	public void Ball(int x, int y) {
		fieldBall= new JLabel();
		fieldBall.setLocation(x * SIZE, y * SIZE);
		fieldBall.setSize(SIZE, SIZE);
		fieldBall.setBackground(Color.black);
		fieldBall.setVisible(true);
		fieldBall.setOpaque(true);
		add(fieldBall);
	}
	
	/**
	 * Methode zum bewegen des Balls
	 */
	public void moveBall(int x, int y) {
		fieldBall.setLocation(x * SIZE, y * SIZE);
	}
	
	/**
	 * Methode zum Erzeugen des Schlägers mit vorgegebenen Zellen größe
	 */
	public void Pad(int player, int y, JLabel pad) {
		player = player - 1;
		pad.setLocation(SIZE * (WIDTH - 1) * player, y * SIZE);
		pad.setSize(SIZE, SIZE * 6);
		pad.setOpaque(true);
		pad.setBackground(Color.black);
		pad.setVisible(true);
		add(pad);
	}
	
	/**
	 * Methode zum bewegen des Schlägers
	 */
	public void movePad(int player, int y) {
		switch(player) {
			case(1):{
				fieldPad1.setLocation(0, y * SIZE);
			}
			case(2):{
				fieldPad2.setLocation((WIDTH-1)*SIZE, y * SIZE);
			}
		}
	}
	
	/**
	 * Methode zum Anzeigen der Error Meldung
	 */
	public void showErrorMessage(String text) {
		messanger.Error(text);
	}
	
	/**
	 * Methode zum Anzeigen der Gewinn Meldung
	 */
	public void showVictoryMessage(int player) {
		messanger.Victory(player);
	}
	
	/**
	 * Methode zum Anzeigen der Halbzeit Meldung
	 */
	public void showHalftimeMessage() {
		messanger.Break();
	}
}
