package de.szut.pong.grafik;

import java.io.File;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.szut.pong.logic.AiInterface;
import de.szut.pong.logic.Logic;

/**
 * Die Menüleiste
 * @author Jan
 */
public class LoaderMenu extends JMenuBar {
	
	private static final long serialVersionUID = -8746678954657597260L;
	private JMenu mnPlay;
	private JMenu mnSettings;
	private JMenuItem mniNew;
	private JMenuItem mniRightKI;
	private JMenuItem mniLeftKI;
	private File workspace;
	private KiLoader kiLoader;
	private AiInterface kiObject;
	private Logic logic;
	private Thread thread;
	
	/**
	 * Erstellen der JMenuBar mit Items und ActionListener für Neustarten
	 */
	public LoaderMenu(Logic logic){
		this.logic = logic;
		kiLoader = new KiLoader();
		mnPlay = new JMenu("Play");
		mnSettings = new JMenu("Settings");
		add(mnPlay);
		add(mnSettings);
		mniNew = new JMenuItem("New Game");
		mnPlay.add(mniNew);
		mniLeftKI = new JMenuItem("Left KI");
		mnSettings.add(mniLeftKI);
		mniRightKI = new JMenuItem("Right KI");
		mnSettings.add(mniRightKI);
		mniNew.addActionListener(e -> {
			if (thread != null) {
				thread.interrupt();
				logic.restart(); //Restartet das Spiel
			}
			thread = new Thread(logic);
			thread.start();
		});
		workspace = new File(System.getProperty("user.dir") + "\\bin\\AI"); //Legt den Startpfad fest
		addActionListener(mniRightKI);
		addActionListener(mniLeftKI);
	}
	
	/**
	 * Methode zum erstellen des ActionLIsteners für die Dateiauswahl
	 */
	public void addActionListener(JMenuItem menuItem) {
		
		menuItem.addActionListener(e -> {
			JFileChooser file = new JFileChooser(); 
			file.setCurrentDirectory(workspace);
			file.setFileFilter(new FileNameExtensionFilter("Class file","class"));
			file.showOpenDialog(null); 
			if (file.getSelectedFile() != null) {
				kiObject = kiLoader.loadKI(file.getSelectedFile()); //Gibt dem KI Loader die ausgewählte Datei
			}
			if (menuItem == mniRightKI) {
				logic.setRightAI(kiObject); //Setzt die Rechte KI
			}
			else {
				logic.setLeftAI(kiObject); //Setzt die Linke KI
			}
		});
	}
}
