package rs.ac.bg.fon.nprog.kontroler;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;
import java.util.Properties;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.forme.FormaMain;
import rs.ac.bg.fon.nprog.forme.FormaPodesavanja;
import rs.ac.bg.fon.nprog.konfiguracija.PodaciZaKonfiguraciju;
import rs.ac.bg.fon.nprog.niti.ServerskaNit;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.administrator.LoginSO;
import rs.ac.bg.fon.nprog.operacije.baletskagrupa.KreirajBaletskuGrupuSO;
import rs.ac.bg.fon.nprog.operacije.baletskagrupa.PromeniPodatkeBaletskeGrupeSO;
import rs.ac.bg.fon.nprog.operacije.baletskagrupa.VratiBaletskuGrupuSO;
import rs.ac.bg.fon.nprog.operacije.baletskagrupa.VratiSveBaletskeGrupeSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.KreirajBaletskogIgracaSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.PromeniPodatkeBaletskogIgracaSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.PronadjiBaletskeIgraceSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.VratiBaletskogIgracaSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.VratiSveBaletskeIgraceSO;
import rs.ac.bg.fon.nprog.operacije.baletskiigrac.ZapamtiUplateBaletskogIgracaSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.KreirajKoreografaSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.ObrisiKoreografaSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.PromeniKoreografaSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.PronadjiKoreografeSO;
import rs.ac.bg.fon.nprog.operacije.koreograf.VratiKoreografaSO;
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
    
    public List<ApstraktniDomenskiObjekat> pronadjiKoreografe(Koreograf koreograf) throws Exception {
        ApstraktnaSO so=new PronadjiKoreografeSO();
        so.execute(koreograf);
        return ((PronadjiKoreografeSO)so).getListaKoreografa();
    }
    
    public Koreograf vratiKoreografa(Koreograf koreograf) throws Exception {
        ApstraktnaSO so=new VratiKoreografaSO();
        so.execute(koreograf);
        return ((VratiKoreografaSO)so).getKoreograf();
    }
    
    public void promeniKoreografa(ApstraktniDomenskiObjekat ado) throws Exception {
        ApstraktnaSO so=new PromeniKoreografaSO();
        so.execute(ado);
    }
    
    public void obrisiKoreografa(Koreograf koreograf) throws Exception {
        ApstraktnaSO so=new ObrisiKoreografaSO();
        so.execute(koreograf);
    }
    
    public List<ApstraktniDomenskiObjekat> vratiSveBaletskeGrupe() throws Exception {
        ApstraktnaSO so=new VratiSveBaletskeGrupeSO();
        so.execute(new BaletskaGrupa());
        return ((VratiSveBaletskeGrupeSO)so).getListaBaletskihGrupa();
    }
    
    public Long kreirajBaletskogIgraca(ApstraktniDomenskiObjekat ado) throws Exception{
        ApstraktnaSO so = new KreirajBaletskogIgracaSO();
        so.execute(ado);
        return ((KreirajBaletskogIgracaSO) so).getIndeks();
    }
    
    public List<ApstraktniDomenskiObjekat> vratiSveBaletskeIgrace() throws Exception {
        ApstraktnaSO so=new VratiSveBaletskeIgraceSO();
        so.execute(new BaletskiIgrac());
        return ((VratiSveBaletskeIgraceSO)so).getListaBaletskihIgraca();
    }
    
    public List<ApstraktniDomenskiObjekat> pronadjiBaletskeIgrace(BaletskiIgrac baletskiIgrac) throws Exception {
        ApstraktnaSO so=new PronadjiBaletskeIgraceSO();
        so.execute(baletskiIgrac);
        return ((PronadjiBaletskeIgraceSO)so).getListaBaletskihIgraca();
    }
    
    public void promeniPodatkeIgraca(ApstraktniDomenskiObjekat ado) throws Exception {
        ApstraktnaSO so=new PromeniPodatkeBaletskogIgracaSO();
        so.execute(ado);
    }

    public BaletskiIgrac vratiBaletskogIgraca(BaletskiIgrac baletskiIgrac) throws Exception {
        ApstraktnaSO so=new VratiBaletskogIgracaSO();
        so.execute(baletskiIgrac);
        //System.out.println(((VratiBaletskogIgracaSO)so).getBaletskiIgrac().getListaUplata());
        return ((VratiBaletskogIgracaSO)so).getBaletskiIgrac();
    }
    
    public void zapamtiUplateBaletskogIgraca(BaletskiIgrac baletskiIgrac) throws Exception {
        ApstraktnaSO so=new ZapamtiUplateBaletskogIgracaSO();
        so.execute(baletskiIgrac);
    }
    
    public Long kreirajBaletskuGrupu(ApstraktniDomenskiObjekat ado) throws Exception {
        ApstraktnaSO so = new KreirajBaletskuGrupuSO();
        so.execute(ado);
        return ((KreirajBaletskuGrupuSO) so).getIndeks();
    }
    
    public BaletskaGrupa vratiBaletskuGrupu(BaletskaGrupa baletskaGrupa) throws Exception {
        ApstraktnaSO so=new VratiBaletskuGrupuSO();
        so.execute(baletskaGrupa);
        //System.out.println(((VratiBaletskogIgracaSO)so).getBaletskiIgrac().getListaUplata());
        return ((VratiBaletskuGrupuSO)so).getBaletskaGrupa();
    }

    public void promeniPodatkeGrupe(BaletskaGrupa baletskaGrupa) throws Exception {
        ApstraktnaSO so=new PromeniPodatkeBaletskeGrupeSO();
        so.execute(baletskaGrupa);
    }
}
