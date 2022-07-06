package rs.ac.bg.fon.nprog.niti;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.nprog.domen.Administrator;
import rs.ac.bg.fon.nprog.transfer.Odgovor;
import rs.ac.bg.fon.nprog.transfer.Operacije;
import rs.ac.bg.fon.nprog.transfer.Posiljalac;
import rs.ac.bg.fon.nprog.transfer.Primalac;
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
                //odgovor = login(zahtev);
                return odgovor;
            
        }
        return null;
    }
}
