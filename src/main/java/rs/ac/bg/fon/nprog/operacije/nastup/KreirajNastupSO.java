package rs.ac.bg.fon.nprog.operacije.nastup;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class KreirajNastupSO extends ApstraktnaSO{
    
    long indeks=-1;

    public KreirajNastupSO() {
        super();
    }
    

    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Nastup)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koreograf!");
        }
        Nastup nastup=(Nastup) param;

        if(nastup.getDatumVremeNastupa().before(new Date())
                || nastup.getDatumVremeNastupa().equals(new Date())){
            throw new Exception("Datum nastupa mora biti posle danasnjeg datuma!");
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
