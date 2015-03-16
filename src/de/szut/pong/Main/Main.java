package de.szut.pong.Main;

import de.szut.pong.grafik.GamePanel;
import de.szut.pong.grafik.MainFrame;
import de.szut.pong.logic.Logic;

/**
 * Startet das Programm
 * @author Alexander
 */
public class Main {
	
	/**
	 * Startet das Programm
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		GamePanel panel = frame.getGamePanel();
		Logic logic = new Logic(panel);
		frame.setMenu(logic);
	}
}