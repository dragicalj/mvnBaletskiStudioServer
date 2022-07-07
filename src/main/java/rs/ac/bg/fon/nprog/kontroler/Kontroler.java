package rs.ac.bg.fon.nprog.kontroler;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Properties;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.forme.FormaMain;
import rs.ac.bg.fon.nprog.forme.FormaPodesavanja;
import rs.ac.bg.fon.nprog.konfiguracija.PodaciZaKonfiguraciju;
import rs.ac.bg.fon.nprog.niti.ServerskaNit;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.administrator.LoginSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.KreirajKoreografaSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.VratiSveKoreografeSO;

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
    
    public void setFormaMain(FormaMain formaMain) {
		this.formaMain = formaMain;
	}
    
    public void konfigurisiBazu(String url, String username, String password, String port) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("url", url);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        properties.setProperty("port", port);
        properties.store(new FileOutputStream(PodaciZaKonfiguraciju.PATH), "");
    }

    public void procitajKonfiguracijuBaze(FormaPodesavanja formaPodesavanja) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PodaciZaKonfiguraciju.PATH));
        formaPodesavanja.getTxtUrl().setText(properties.getProperty("url"));
        formaPodesavanja.getTxtUsername().setText(properties.getProperty("username"));
        formaPodesavanja.getTxtPassword().setText(properties.getProperty("password"));
        formaPodesavanja.getTxtPort().setText(properties.getProperty("port"));
    }
    
    public ApstraktniDomenskiObjekat login(ApstraktniDomenskiObjekat ado) throws Exception {
        ApstraktnaSO so = new LoginSO();
        so.execute(ado);
        return ((LoginSO) so).getAdo();
    }
    
    public Long kreirajKoreografa(ApstraktniDomenskiObjekat ado) throws Exception {
        ApstraktnaSO so = new KreirajKoreografaSO();
        so.execute(ado);
        return ((KreirajKoreografaSO) so).getIndeks();
    }
    
    public List<ApstraktniDomenskiObjekat> vratiSveKoreografe() throws Exception {
        ApstraktnaSO so=new VratiSveKoreografeSO();
        so.execute(new Koreograf());
        return ((VratiSveKoreografeSO)so).getListaKoreografa();
    }
}
