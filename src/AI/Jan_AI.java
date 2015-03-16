package AI;

import de.szut.pong.logic.AiInterface;

/**
 * Eine KI die die Position des Balls berechnet und dann Versucht die Position zu erreichen um den Ball bestm�glich zur�ckzuschlagen
 * @author Jan-Philipp
 */
public class Jan_AI implements AiInterface {
	private int oldballposx;
	private int oldballposy;
	private int ballx;
	private int bally;
	private int startpad;
	private double m;
	private double b;
	private boolean side;	
	private boolean start = true;
	private boolean alreadyCollided = false;
	private boolean collision;

	
	/**
	 * Setzt die Seite auf der die KI sich befindet
	 * @param side = Die Seite auf der die KI sich befindet
	 * 			false = rechts
	 * 			true = links
	 */
	@Override
	public void setSide(boolean side) {
		this.side = side;
	}
	/**
	 * Wird aufgerufen sobald ein Punkt erzielt wurde
	 * Setzt die alte Position des Balls auf Null
	 */
	@Override
	public void newGame() {
		oldballposx = 0;
		oldballposy = 0;
		start = true;
	}
	
	/**
	 * Bekommt vom Spiel die Position des eigenen Schl�gers, des gegnerischen Schl�gers sowie die Position des Balls
	 * �berpr�ft auf welcher Seite des Spielfelds die KI ist
	 * L�sst den Schl�ger zum Startpunkt zur�ckkehren wenn der Ball von ihm Wegfliegt
	 * Positioniert den Schl�ger bei zukommendem Ball bestm�glich um den Ball zur�ckzuschlagen
	 */
	@Override
	public int moveDecision(int mypanel, int enemypanel, int ballx, int bally) {
		this.ballx = ballx;
		this.bally = bally;
		if(start){
			startpad = mypanel;
		}
		if (start || collision){
			oldballposx = ballx;
			oldballposy = bally;
			start = false;
			collision = false;
		}else{
			if(collision()){
				collision = true;
			}else{
				if(side){
					if(!balldirection()){
						return returntostart(mypanel);
					}else if(balldirection()){
						way();
						double wayy = wayy(1);
						if (timehitwall(wayy) % 2 ==1){
							int y = 60 - Math.abs((int) wayy % 60);
							return movetoball(mypanel, y, enemypanel);
						}else{
							int y = Math.abs((int) wayy % 60);
							return movetoball(mypanel, y, enemypanel);
						}
					}
				}
				else{
					if(!balldirection()){
						return returntostart(mypanel);
					} else if(balldirection()){
						way();
						double wayy = wayy(63);
						if (timehitwall(wayy) % 2 ==1){
							int y = 60 - Math.abs((int) wayy % 60);
							return movetoball(mypanel, y, enemypanel);
						}else{
							int y = Math.abs((int) wayy % 60);
							return movetoball(mypanel, y, enemypanel);
						}
					}
				}	
			}
			return 0;
		}
		return 0;
	}
	
	/**
	 * �berpr�ft ob der Ball sich von der KI weg bewegt oder sich ihr ann�hert
	 */
     public boolean balldirection(){
    	 if (side && oldballposx - ballx > 0){
    		 return true;
    	 } else if(!side && oldballposx - ballx < 0){
    		 return true;
    	 }
    	 return false;
     }
     
     /**
      * Bestimmt die Steigung und den Schnittpunkt mit der Y-Achse des Balls
      */
     public void way(){
    	 m = ((double) bally - (double) oldballposy)/((double) ballx - (double) oldballposx);
         b = (double) bally - (m * ballx);
     }
     /**
      * Gibt die Y-Koordinate an wo der Ball landen wird
      */
     public double wayy(double x){
    	 return m*x+b;
     }
     /**
      * Gibt die anzahl an Collisionen des Balls an
      */
     public int timehitwall(double y){
    	 if (y < 0 && y / 60 > -1 && y / 60 < -2) {
             return (int) (y / 60) + 1;
    	 }
     return (int) y / 60;
     }
     /**
      * �berpr�ft ob der Ball gegen eine Wand prallt
      */
     public boolean collision(){
    	 if((ballx == 1 || ballx == 63 || bally == 0 || bally == 59) && !alreadyCollided) {
             alreadyCollided = true;
             return true;
    	 }
    	 if(bally > 0 && bally < 59) {
             alreadyCollided = false;
    	 }
     return false;
     }
     /**
      * L�sst den Schl�ger wieder in seine ausgangs Position zur�ckkehren
      */
     public int returntostart(int mypanel){
    	 if(mypanel < startpad){
				return 1;
			}else if(mypanel > startpad){
				return -1;
			}else{
				return 0;
			} 
     }
     /**
      * �berpr�ft wo sich der Gegnerische Schl�ger im Gegensatz zum eigenen Befindet und den aufschlag Punkt am Schl�ger zu Bestimmen
      * Positioniert den Schl�ger so das er den Ball an der Gew�nschte schl�ger stelle trifft
      */
     public int movetoball(int mypanel, int y, int enemypanel){
    	 int x = 0;
    	 if (enemypanel < mypanel){
    		 x = 1;
    	 }else if (enemypanel > mypanel){
    		 x = 4;
    	 }
    	 if (mypanel+x < y){
				return 1;
			}else if (mypanel+x >y){
				return -1;
			}else{
				return 0;
			}
     }
}