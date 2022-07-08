package rs.ac.bg.fon.nprog.niti;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.nprog.domen.Administrator;
import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.kontroler.Kontroler;
import rs.ac.bg.fon.nprog.transfer.Odgovor;
import rs.ac.bg.fon.nprog.transfer.Operacije;
import rs.ac.bg.fon.nprog.transfer.Posiljalac;
import rs.ac.bg.fon.nprog.transfer.Primalac;
import rs.ac.bg.fon.nprog.transfer.TipOdgovora;
import rs.ac.bg.fon.nprog.transfer.Zahtev;

public class KlijentskaNit extends Thread{
	
	private Socket socket;
    private Administrator trenutniAdministrator;
    private ServerskaNit serverskaNit;

    public KlijentskaNit(Socket socket, ServerskaNit serverskaNit) throws IOException {
        this.socket = socket;
        this.serverskaNit = serverskaNit;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                Zahtev zahtev = (Zahtev) new Primalac(socket).primi();
                Odgovor odgovor = handleRequest(zahtev);
                new Posiljalac(socket).posalji(odgovor);
            } catch (Exception ex) {
                Logger.getLogger(KlijentskaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    private Odgovor handleRequest(Zahtev zahtev) {
        int operation = zahtev.getOperacija();
        Odgovor odgovor = new Odgovor();
        switch (operation) {
            case Operacije.LOGIN:
                odgovor = login(zahtev);
                return odgovor;
            case Operacije.KREIRAJ_KOREOGRAFA:
                odgovor = kreirajKoreografa(zahtev);
                return odgovor;
            case Operacije.UCITAJ_KOREOGRAFE:
                odgovor = ucitajKoreografe();
                return odgovor;
            case Operacije.PRONADJI_KOREOGRAFE:
                odgovor = pronadjiKoreografe(zahtev);
                return odgovor; 
            case Operacije.UCITAJ_KOREOGRAFA:
                odgovor = ucitajKoreografa(zahtev);
                return odgovor; 
            case Operacije.PROMENI_KOREOGRAFA:
                odgovor = promeniKoreografa(zahtev);
                return odgovor; 
            case Operacije.OBRISI_KOREOGRAFA:
                odgovor = obrisiKoreografa(zahtev);
                return odgovor; 
            case Operacije.UCITAJ_BALETSKEGRUPE:
                odgovor = ucitajBaletskeGrupe();
                return odgovor;
            case Operacije.KREIRAJ_BALETSKOGIGRACA:
                odgovor = kreirajBaletskogIgraca(zahtev);
                return odgovor;
            case Operacije.UCITAJ_BALETSKEIGRACE:
                odgovor = ucitajBaletskeIgrace();
                return odgovor;
            case Operacije.PRONADJI_BALETSKEIGRACE:
                odgovor = pronadjiBaletskeIgrace(zahtev);
                return odgovor;    
            case Operacije.UCITAJ_BALETSKOGIGRACA:
                odgovor = ucitajBaletskogIgraca(zahtev);
                return odgovor; 
            case Operacije.PROMENI_PODATKEBALETSKOGIGRACA:
                odgovor = promeniPodatkeIgraca(zahtev);
                return odgovor; 
            case Operacije.ZAPAMTI_UPLATEBALETSKOGIGRACA:
                odgovor = zapamtiUplateBaletskogIgraca(zahtev);
                return odgovor;
            case Operacije.KREIRAJ_BALETSKUGRUPU:
                odgovor = kreirajBaletskuGrupu(zahtev);
                return odgovor;
            case Operacije.UCITAJ_BALETSKUGRUPU:
                odgovor = ucitajBaletskuGrupu(zahtev);
                return odgovor;
            case Operacije.PROMENI_PODATKEBALETSKEGRUPE:
                odgovor = promeniPodatkeGrupe(zahtev);
                return odgovor;
        }
        return null;
    }
    
    private Odgovor login(Zahtev zahtev) {
        Administrator administrator = (Administrator) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            ApstraktniDomenskiObjekat ado = Kontroler.getInstance().login(administrator);
            odgovor.setRezultat(administrator);
            this.trenutniAdministrator = administrator;
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;

    }
    
    private Odgovor kreirajKoreografa(Zahtev zahtev) {
        Koreograf koreograf = (Koreograf) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Long indeks = Kontroler.getInstance().kreirajKoreografa(koreograf);
            koreograf.setKoreografId(indeks);
            odgovor.setRezultat(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajKoreografe() {
        Odgovor odgovor=new Odgovor();
        try {
            List<ApstraktniDomenskiObjekat> koreografi = Kontroler.getInstance().vratiSveKoreografe();
            odgovor.setRezultat(koreografi);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor pronadjiKoreografe(Zahtev zahtev) {
        List<ApstraktniDomenskiObjekat> lista;
        Koreograf koreograf = (Koreograf) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            lista = Kontroler.getInstance().pronadjiKoreografe(koreograf);
            
            odgovor.setRezultat(lista);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajKoreografa(Zahtev zahtev) {
        Koreograf koreograf = (Koreograf) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().vratiKoreografa(koreograf);
            odgovor.setRezultat(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor promeniKoreografa(Zahtev zahtev) {
        Koreograf koreograf = (Koreograf) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().promeniKoreografa(koreograf);
            //odgovor.setRezultat(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor obrisiKoreografa(Zahtev zahtev) {
        Koreograf koreograf = (Koreograf) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().obrisiKoreografa(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajBaletskeGrupe() {
        Odgovor odgovor=new Odgovor();
        try {
            List<ApstraktniDomenskiObjekat> baletskeGrupe = Kontroler.getInstance().vratiSveBaletskeGrupe();
            odgovor.setRezultat(baletskeGrupe);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor kreirajBaletskogIgraca(Zahtev zahtev) {
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Long indeks = Kontroler.getInstance().kreirajBaletskogIgraca(baletskiIgrac);
            baletskiIgrac.setBaletskiIgracId(indeks);
            odgovor.setRezultat(baletskiIgrac);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajBaletskeIgrace() {
        Odgovor odgovor=new Odgovor();
        try {
            List<ApstraktniDomenskiObjekat> baletskiIgraci = Kontroler.getInstance().vratiSveBaletskeIgrace();
            odgovor.setRezultat(baletskiIgraci);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor pronadjiBaletskeIgrace(Zahtev zahtev) {
        List<ApstraktniDomenskiObjekat> lista;
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            lista=Kontroler.getInstance().pronadjiBaletskeIgrace(baletskiIgrac);
            odgovor.setRezultat(lista);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajBaletskogIgraca(Zahtev zahtev) {
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            BaletskiIgrac baletskiIgrac1=Kontroler.getInstance().vratiBaletskogIgraca(baletskiIgrac);
            //System.out.println(baletskiIgrac1.getListaUplata());
            odgovor.setRezultat(baletskiIgrac1);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor promeniPodatkeIgraca(Zahtev zahtev) {
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().promeniPodatkeIgraca(baletskiIgrac);
            //odgovor.setRezultat(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor zapamtiUplateBaletskogIgraca(Zahtev zahtev) {
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().zapamtiUplateBaletskogIgraca(baletskiIgrac);
            //odgovor.setRezultat(koreograf);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor kreirajBaletskuGrupu(Zahtev zahtev) {
        BaletskaGrupa baletskaGrupa = (BaletskaGrupa) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Long indeks = Kontroler.getInstance().kreirajBaletskuGrupu(baletskaGrupa);
            baletskaGrupa.setBaletskaGrupaId(indeks);
            odgovor.setRezultat(baletskaGrupa);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
    
    private Odgovor ucitajBaletskuGrupu(Zahtev zahtev) {
        BaletskaGrupa baletskaGrupa = (BaletskaGrupa) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            BaletskaGrupa baletskaGrupa1=Kontroler.getInstance().vratiBaletskuGrupu(baletskaGrupa);
            //System.out.println(baletskiIgrac1.getListaUplata());
            odgovor.setRezultat(baletskaGrupa1);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }

    private Odgovor promeniPodatkeGrupe(Zahtev zahtev) {
        BaletskaGrupa baletskaGrupa = (BaletskaGrupa) zahtev.getArgument();
        Odgovor odgovor = new Odgovor();
        try {
            Kontroler.getInstance().promeniPodatkeGrupe(baletskaGrupa);
            odgovor.setTipOdgovora(TipOdgovora.USPESNO);
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setTipOdgovora(TipOdgovora.GRESKA);
            odgovor.setException(ex);
        }
        return odgovor;
    }
}
