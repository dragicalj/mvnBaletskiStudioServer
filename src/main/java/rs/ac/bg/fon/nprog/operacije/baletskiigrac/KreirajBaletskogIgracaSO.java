package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;
import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class KreirajBaletskogIgracaSO extends ApstraktnaSO {

    long indeks = -1;

    public KreirajBaletskogIgracaSO() {
        super();
    }
    
    public KreirajBaletskogIgracaSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskiIgrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskiIgrac!");
        }
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) param;
        System.out.println(baletskiIgrac.getListaUplata());
        if (baletskiIgrac.getDatumRodjenja().after(new Date())
                || baletskiIgrac.getDatumRodjenja().equals(new Date())) {
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        BaletskiIgrac baletskiIgrac = (BaletskiIgrac) param;
        indeks = repository.kreirajSlog((ApstraktniDomenskiObjekat) baletskiIgrac);
        System.out.println(indeks);
        baletskiIgrac.setBaletskiIgracId(indeks);

        for (Uplata uplata : baletskiIgrac.getListaUplata()) {
            uplata.setBaletskiIgrac(baletskiIgrac);
            repository.kreirajSlog((Uplata) uplata);
        }
    }

    public long getIndeks() {
        return indeks;
    }

}
