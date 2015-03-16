package de.szut.pong.logic;

import de.szut.pong.grafik.GamePanel;

/**
 * 
 * @author Simeon
 */
public class Player {
	
	boolean left;
	int position;
	
	/**
	 * Konstruktor
	 * @param position
	 */
	public Player(int position) {
		this.position = position;
	}
	
	/**
	 * Gibt die X Position des Spielers zurück
	 * @return = X Position des Spielers
	 */
	public int getPos() {
		return position;
	}
	
	/**
	 * Bewegt den Spieler
	 * @param i = Gibt die Richtung an in die der Spieler bewegt wird
	 */
	public void move(int i) {
		if (i < 0 && position > 0) {
			position -= 1;
		}
		else if (i > 0 && position < GamePanel.HEIGHT-6) {
			position += 1;
		}
	}
}
