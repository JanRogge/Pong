package de.szut.pong.grafik;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.szut.pong.logic.Logic;

/**
 * Das Frame
 * @author Jan
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -8163322885564242062L;
	private GamePanel startGui = new GamePanel();
	private final Dimension d = this.getToolkit().getScreenSize();
	private LoaderMenu menu;
	
	public MainFrame() {
		setAlwaysOnTop(true);
		setTitle("Pong");
		setLayout(null);
		setResizable(false);
		setContentPane(startGui);
		setSize(startGui.getWidth() - 10, startGui.getHeight() + 13); 
		setLocation((int) ((d.getWidth() - this.getWidth()) / 2), (int) ((d.getHeight() - this.getHeight()) / 2)); //Positioniert dsa Fenster in der Mitte des Bildschirms
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Getter des Panels
	 */
	public GamePanel getGamePanel() {
		return startGui;
	}
	/**
	 * Setzten der Menubar
	 */
	public void setMenu(Logic logic) {
		menu = new LoaderMenu(logic);
		setJMenuBar(menu);
		setVisible(true);
	}
}