package de.szut.pong.logic;

/**
 * Das KI Interface
 * @author Alexander
 */
public interface AiInterface {
	
	/**
	 * Setzt die Seite auf der die KI sich befindet
	 * @param side = Die Seite auf der die KI sich befindet
	 * 			false = links
	 * 			true = rechts
	 */
	public void setSide(boolean side);
	
	/**
	 * Vermittelt der KI, dass eine neue Runde begonnen hat
	 */
	public void newGame();
	
	/**
	 * Fragt die KI nach einer Bewegungsentscheidung
	 * @param mypanel = Die Eigene Position
	 * @param enemypanel = Die Position des Gegners
	 * @param ballx = Die X Koordinate des Balls
	 * @param bally = Die Y Koordinate des Balls
	 * @return = Die Bewegungsentscheidung
	 * 			positiv = Bewegung nach unten
	 * 			null = keine Bewegung
	 * 			negativ = Bewegung nach oben
	 */
	public int moveDecision(int mypanel, int enemypanel, int ballx, int bally);
}
