package rs.ac.bg.fon.nprog.kontroler;

import rs.ac.bg.fon.nprog.forme.FormaMain;
import rs.ac.bg.fon.nprog.niti.ServerskaNit;

public class Kontroler {
	
	private static Kontroler instance;
   
    private Kontroler() {
    }
    
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }
}
