package rs.ac.bg.fon.nprog.niti;

import java.io.IOException;
import java.net.Socket;
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

}
