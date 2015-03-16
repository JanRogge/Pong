package de.szut.pong.logic;

import de.szut.pong.grafik.GamePanel;

/**
 * 
 * @author Simeon
 */
public class Ball {
	
	double x;
	double y;
	double angle;
	double speed;
	
	/**
	 * Konstruktor
	 * @param side = Seite auf der der Ball spawnt
	 * 			false = links
	 * 			true = rechts
	 */
	public Ball(boolean side) {
		if (side) {
			x = GamePanel.WIDTH-1;
		}
		else {
			x = 1;
		}
		y = GamePanel.HEIGHT/2;
		speed = 0.5;
		angle = (Math.random() - 0.5) * 140;
		if (side) {
			angle = 180 * angle / Math.abs(angle) - angle;
		}
	}
	
	/**
	 * Gibt die X Position zurück
	 * @return = x
	 */
	public int getX() {
		return (int) x;
	}
	
	/**
	 * Gibt die Y Position zurück
	 * @return = y
	 */
	public int getY() {
		return (int) y;
	}
	
	/**
	 * Bewegt den Ball
	 * @param posPlayer1 = Position des linken Spielers
	 * @param posPlayer2 = Position des rechten Spielers
	 * @return = Flag welche angibt ob ein Punkt erziehlt wurde
	 * 			false = Kein Punkt
	 * 			true = Punkt
	 */
	protected boolean move(int posPlayer1, int posPlayer2) {
		double altX = x;
		double altY = y;
		boolean end = false;
		
		x += Math.cos(Math.toRadians(angle))*speed;
		y += Math.sin(Math.toRadians(angle))*speed;
		
		if (x < 1) { //Kollision auf der linken Seite
			speedUp();
			double y1 = hitWall(altY - (altX - 1)/(altX - x) * (altY - y), false); //y position bei 1
			if (posPlayer1 < y1 && y1 < posPlayer1 + 6) { //Schläger getroffen
				angle = 180 * angle / Math.abs(angle) - angle;
				x = 2 - x;
				if (posPlayer1 < y1 && y1 < posPlayer1 + 2) { //Oberes Schlägerdrittel
					angle = (70 + angle) * Math.random() - 70;
				}
				else if (posPlayer1 + 4 < y1 && y1 < posPlayer1 + 6) { //Unteres Schlägerdrittel
					angle = (70 - angle) * Math.random() + angle;
				}
			}
			else {
				end = true;
			}
		}
		else if (x > 64) { //Kollision auf der rechten Seite
			speedUp();
			double y64 = hitWall(altY - (altX - 64)/(altX - x) * (altY - y), false); //y position bei 64
			if (posPlayer2 < y64 && y64 < posPlayer2 + 6) { //Schläger getroffen
				if (posPlayer2 < y64 && y64 < posPlayer2 + 2) { //Oberes Schlägerdrittel
					angle = (70 + angle) * Math.random() - 70;
				}
				else if (posPlayer2 + 4 < y64 && y64 < posPlayer2 + 6) { //Unteres Schlägerdrittel
					angle = (70 - angle) * Math.random() + angle;
				}
				if (angle == 0) {
					angle = 180;
				}
				else {
					angle = 180 * angle/Math.abs(angle) - angle;
				}
				x = 128 - x;
			}
			else {
				end = true;
			}
		}
		y = hitWall(y, true);
		return end;
	}
	
	
	/**
	 * Lässt den Ball von der oberen/unteren Wand abprallen
	 * @param y = Position des Balls
	 * @param turnAngle = Flag welche angibt ob der Winkel umgedreht werden soll
	 * @return = neue y Position
	 */
	protected double hitWall(double y, boolean turnAngle) {
		if (y < 0) { //Unten abprallen
			y = Math.abs(y);
			angle *= Boolean.compare(!turnAngle, turnAngle); //Invertiert den Winkel wenn turnAngle
		}
		else if (y > 60) { //Oben abprallen
			y = 120 - y;
			angle *= Boolean.compare(!turnAngle, turnAngle); //Invertiert den Winkel wenn turnAngle
		}
		return y;
	}
	
	/**
	 * Erhöht die Geschwindigkeit des Balls
	 */
	private void speedUp() {
		speed *= 1.05;
	}
}
