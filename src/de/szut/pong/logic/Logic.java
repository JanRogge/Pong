package de.szut.pong.logic;

import de.szut.pong.grafik.GamePanel;

/**
 * Steuert den Programmablauf
 * @author Simeon
 */
public class Logic implements Runnable {
	
	Ball ball;
	Player leftPlayer;
	Player rightPlayer;
	AiInterface ai1;
	AiInterface ai2;
	GamePanel gui;
	int posPlayer1;
	int posPlayer2;
	int rightPoints;
	int leftPoints;
	boolean firstRound;
	
	/**
	 * Konstruktor
	 * @param gui = Anzeigefläche der Bilddaten
	 */
	public Logic(GamePanel gui) {
		this.gui = gui;
		ball = new Ball(firstRound);
		leftPlayer = new Player(GamePanel.HEIGHT/2 - 3);
		rightPlayer = new Player(GamePanel.HEIGHT/2 - 3);
	}
	
	/**
	 * Sorgt für den Aufruf der Bewegungsmethoden
	 */
	public void run() {
		boolean end = false;
		boolean aiLoaded = true;
		if (ai1 == null) { // 
			gui.showErrorMessage("Left AI is not defined");
			aiLoaded = false;
		}
		if (ai2 == null) {
			gui.showErrorMessage("Right AI is not defined");
			aiLoaded = false;
		}
		rightPoints = 0;
		leftPoints = 0;
		if (aiLoaded) {
			ai1.setSide(true);
			ai2.setSide(false);
			int ballX;
			int ballY;
			int runVar = 0;
			while (!end) {
				ballX = ball.getX();
				ballY = ball.getY();
				if (runVar == 0) {
					runVar = 3;
					informAI(ballX, ballY, true);
				}
				else {
					runVar--;
					informAI(ballX, ballY, false);
				}
				moveBall(ballX, ballY);
				gui.draw(leftPlayer.getPos(), rightPlayer.getPos(), ballX, ballY);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					end = true;
				}
			}
		}
	}
	
	/**
	 * Informiert die KI über die aktuelle Ballposition und bewegt wenn movePlayer den Spieler
	 * @param ballX = aktuelle Ball X-Koordinate
	 * @param ballY = aktuelle Ball Y-Koordinate
	 * @param movePlayer = Wenn true bewegung des Spielers sonst nichts
	 */
	private void informAI(int ballX, int ballY, boolean movePlayer) {
		posPlayer1 = leftPlayer.getPos();
		posPlayer2 = rightPlayer.getPos();
		int moveDecision1 = ai1.moveDecision(posPlayer1, posPlayer2, ballX, ballY);
		int moveDecision2 = ai2.moveDecision(posPlayer2, posPlayer1, ballX, ballY);
		if (movePlayer) {
			leftPlayer.move(moveDecision1);
			rightPlayer.move(moveDecision2);
		}
	}
	
	/**
	 * Sorgt für die bewegung des Balls
	 * @param ballX = X Koordinate des Balls
	 * @param ballY = Y Koordinate des Balls
	 */
	private void moveBall(int ballX, int ballY) {
		posPlayer1 = leftPlayer.getPos();
		posPlayer2 = rightPlayer.getPos();
		if (ball.move(posPlayer1, posPlayer2)) { //Runde vorbei?
			if (ball.getX() <= 0) {
				rightPoints += 1;
				ai1.newGame();
				ai2.newGame();
				if (rightPoints == 5 && !firstRound) {
					gui.showHalftimeMessage();
					firstRound = true;
				}
				else if (rightPoints == 10) {
					gui.showVictoryMessage(2);
				}
				gui.setPoints(2, rightPoints);
			}
			else {
				leftPoints += 1;
				ai1.newGame();
				ai2.newGame();
				if (leftPoints == 5 && !firstRound) {
					gui.showHalftimeMessage();
					firstRound = true;
				}
				else if (leftPoints == 10) {
					gui.showVictoryMessage(1);
				}
				gui.setPoints(1, leftPoints);
			}
			ball = new Ball(firstRound);
			ballX = ball.getX();
			ballY = ball.getY();
			posPlayer1 = GamePanel.HEIGHT/2 - 3;
			posPlayer2 = GamePanel.HEIGHT/2 - 3;
			leftPlayer = new Player(posPlayer1);
			rightPlayer = new Player(posPlayer2);
		}
	}
	
	/**
	 * Startet eine neue Runde
	 */
	public void restart() {
		rightPoints = 0;
		leftPoints = 0;
		firstRound = false;
		ball = new Ball(firstRound);
		leftPlayer = new Player(GamePanel.HEIGHT/2 - 3);
		rightPlayer = new Player(GamePanel.HEIGHT/2 - 3);
		posPlayer1 = GamePanel.HEIGHT/2 - 3;
		posPlayer2 = GamePanel.HEIGHT/2 - 3;
	}
	
	/**
	 * Setzt die linke KI
	 * @param ai = zu setzende KI
	 */
	public void setLeftAI(AiInterface ai) {
		if (ai != null) {
			ai1 = ai;
		}
		else {
			gui.showErrorMessage("Can't load AI");
		}
	}
	
	/**
	 * Setzt die rechte KI
	 * @param ai = zu setzende KI
	 */
	public void setRightAI(AiInterface ai) {
		if (ai != null) {
			ai2 = ai;
		}
		else {
			gui.showErrorMessage("Can't load AI");
		}
	}
}