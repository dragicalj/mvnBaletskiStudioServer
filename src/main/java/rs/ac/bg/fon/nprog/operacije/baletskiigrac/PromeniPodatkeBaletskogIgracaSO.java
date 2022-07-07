package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class PromeniPodatkeBaletskogIgracaSO extends ApstraktnaSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskiIgrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskiIgrac!");
        }
        BaletskiIgrac baletskiIgrac=(BaletskiIgrac) param;
        System.out.println(baletskiIgrac.getListaUplata());
        if(baletskiIgrac.getDatumRodjenja().after(new Date())
                || baletskiIgrac.getDatumRodjenja().equals(new Date())){
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.promeni((ApstraktniDomenskiObjekat)param);
    }
    
}

