package AI;

import de.szut.pong.logic.AiInterface;

/**
 * Eine KI die Versucht die eigene Position der Position des Balles anzugleichen
 * @author Alexander
 */
public class Dummy implements AiInterface {
	int difference = 0;
	int panelmiddle = 2;
	int move = 0;
	
	@Override
	public void setSide(boolean side) {
	}
	
	@Override
	public void newGame() {
	}
	
	@Override
	public int moveDecision(int mypanel, int enemypanel, int ballx, int bally) {
		difference = ((mypanel + panelmiddle) - bally) * (-1);
		return difference;
	}
}