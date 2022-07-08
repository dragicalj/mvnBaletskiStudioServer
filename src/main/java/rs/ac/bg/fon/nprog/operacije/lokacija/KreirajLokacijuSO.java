package rs.ac.bg.fon.nprog.operacije.lokacija;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class KreirajLokacijuSO extends ApstraktnaSO{
    
    long indeks=-1;

    public KreirajLokacijuSO() {
        super();
    }

    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        indeks=repository.kreirajSlog((ApstraktniDomenskiObjekat)param);

    }

    public long getIndeks() {
        return indeks;
    }
    
}

