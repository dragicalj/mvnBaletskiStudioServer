package rs.ac.bg.fon.nprog.kontroler;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import rs.ac.bg.fon.nprog.forme.FormaMain;
import rs.ac.bg.fon.nprog.konfiguracija.PodaciZaKonfiguraciju;
import rs.ac.bg.fon.nprog.niti.ServerskaNit;

public class Kontroler {
	
	private static Kontroler instance;
	private ServerskaNit serverskaNit;
    private FormaMain formaMain;
   
    private Kontroler() {
    }
    
    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }
    
    public void pokreniServer(FormaMain formaMain) throws IOException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PodaciZaKonfiguraciju.PATH));
        int port = -1;
        if (!"".equals(properties.getProperty("port"))) {
            port = Integer.parseInt(properties.getProperty("port"));
        } else {
            throw new Exception();
        }
        ServerSocket serverSocket = new ServerSocket(port);
        serverskaNit = new ServerskaNit(serverSocket);
        serverskaNit.start();
        formaMain.getBtnPokreni().setEnabled(false);
        formaMain.getJprogress().setIndeterminate(true);
        formaMain.getBtnZaustavi().setEnabled(true);
        formaMain.getTxtStatus().setText("Server je pokrenut");
        formaMain.getTxtStatus().setForeground(Color.GREEN);
    }

    public void zaustaviServer(FormaMain formaMain) throws IOException {
        serverskaNit.zaustavi();
        formaMain.getBtnPokreni().setEnabled(true);
        formaMain.getBtnZaustavi().setEnabled(false);
        formaMain.getTxtStatus().setText("Server je zaustavljen");
        formaMain.getTxtStatus().setForeground(Color.RED);
    }
}
