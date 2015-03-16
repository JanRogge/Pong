package de.szut.pong.grafik;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Die Punkteanzeige
 * @author Jan
 */
public class Points {
	
	private JLabel points;
	private JLabel[][] pointList = new JLabel[2][7];
	private JPanel panel;

	/**
	 * Methode zum erstellen der Labels die die Siebensegment anzeige erzeugen
	 */
	public Points(int player, GamePanel panel){
		this.panel = panel;
		player = player-1;
		int SIZE = GamePanel.SIZE;
		int WIDTH = GamePanel.WIDTH;
		int SIZEx = (int) (SIZE*2.5);
		int SIZEy = SIZE;
		int ges = SIZEy + SIZEx;
		int startlocx = SIZE * (WIDTH - 5)/4;
		int startlocy = SIZE * 10;
		for(int x = 0; x < 3; x++) {
			points = new JLabel("");
			points.setLocation((startlocx + (int) (SIZE * (WIDTH + 1.0)/2) * player), startlocy + ges * x);
			points.setSize(SIZEx, SIZEy);
			points.setBackground(Color.green);
			if(x != 1){
				points.setOpaque(true);	
			}
			pointList[player][x] = points;
			panel.add(points);
		}
		for(int x = 0; x < 4; x++) {			
			points = new JLabel("");
			points.setLocation((startlocx - SIZEy) + ges * (x%2) + (int) (SIZE * (WIDTH + 1.0)/2) * player, (startlocy + SIZEy) + x/2 * ges);
			points.setSize(SIZEy, SIZEx);
			points.setBackground(Color.green);
			points.setOpaque(true);
			pointList[player][x+3] = points;
			panel.add(points);
		}			
	}
	
	/**
	 * Methode zum Setzten der Punkteanzeige
	 */
	public void setPoints(int player, int point) {
		player = player-1;
		switch(point) {
			case(0):{
				pointList[player][0].setOpaque(true);
				pointList[player][1].setOpaque(false);
				pointList[player][2].setOpaque(true);
				pointList[player][3].setOpaque(true);
				pointList[player][4].setOpaque(true);
				pointList[player][5].setOpaque(true);
				pointList[player][6].setOpaque(true);
				break;
			}
			case(1):{
				pointList[player][0].setOpaque(false);
				pointList[player][2].setOpaque(false);
				pointList[player][3].setOpaque(false);
				pointList[player][5].setOpaque(false);
				break;
			}
			case(2):{
				pointList[player][0].setOpaque(true);
				pointList[player][1].setOpaque(true);
				pointList[player][2].setOpaque(true);
				pointList[player][5].setOpaque(true);
				pointList[player][6].setOpaque(false);
				break;
			}
			case(3):{
				pointList[player][5].setOpaque(false);
				pointList[player][6].setOpaque(true);
				break;
			}
			case(4):{
				pointList[player][0].setOpaque(false);
				pointList[player][2].setOpaque(false);
				pointList[player][3].setOpaque(true);
				break;
			}
			case(5):{
				pointList[player][0].setOpaque(true);
				pointList[player][2].setOpaque(true);
				pointList[player][4].setOpaque(false);
				break;
			}
			case(6):{
				pointList[player][5].setOpaque(true);
				break;
			}
			case(7):{
				pointList[player][1].setOpaque(false);
				pointList[player][2].setOpaque(false);
				pointList[player][3].setOpaque(false);
				pointList[player][4].setOpaque(true);
				pointList[player][5].setOpaque(false);
				break;
			}
			case(8):{
				pointList[player][1].setOpaque(true);
				pointList[player][2].setOpaque(true);
				pointList[player][3].setOpaque(true);
				pointList[player][5].setOpaque(true);
				break;
			}
			case(9):{
				pointList[player][5].setOpaque(false);
				break;
			}
		}
		panel.repaint();
		panel.revalidate();
	}
}