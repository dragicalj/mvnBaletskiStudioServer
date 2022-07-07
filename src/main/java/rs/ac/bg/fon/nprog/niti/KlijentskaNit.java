package rs.ac.bg.fon.nprog.niti;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.nprog.domen.Administrator;
import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
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

}
