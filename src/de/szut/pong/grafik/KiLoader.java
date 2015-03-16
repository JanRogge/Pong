package de.szut.pong.grafik;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import de.szut.pong.logic.AiInterface;

/**
 * Lädt aus einem übergebenen Dateipfad die KI
 * @author Alexander
 */
public class KiLoader  {
	
	private String className;
	private URL classUrl;
	private URLClassLoader classLoader;
	private Object kiObjekt;
	private Class<?> kiClass;
	private static final String packageName = "AI";
	
	/**
	 * Lädt die KI
	 * @param file = Pfad der KI
	 * @return = Die KI
	 * 			Gibt null zurück wenn die KI nicht geladen werden konnte
	 */
    public AiInterface loadKI(File file) {
		try {
			classUrl = file.getParentFile().getParentFile().toURI().toURL();
		} catch (MalformedURLException e1) {
			return null;
		}
    	classLoader = new URLClassLoader(new URL[]{classUrl}, this.getClass().getClassLoader());
    	className = file.getName();
    	int index = className.indexOf(".");
		try {
			kiClass = classLoader.loadClass(packageName + "." + className.substring(0, index));
		} catch (ClassNotFoundException e1) {
			return null;
		}
		try {
			kiObjekt = kiClass.newInstance();
		} catch (Exception e) {
			return null;
		}
    	if(kiObjekt instanceof AiInterface) {
    		return (AiInterface) kiObjekt;
    	}
		return null;
    }

}
