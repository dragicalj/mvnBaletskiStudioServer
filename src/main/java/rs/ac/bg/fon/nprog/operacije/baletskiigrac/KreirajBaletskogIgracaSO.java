package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;
import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za kreiranje baletskog igraca. Baletsk igrac se pamti
 * u bazi podataka, a rezultat operacije je indeks kreiranog baletskog igraca. Baletski igrac sadrzi
 * listu njegovih uplata clanarina. Uplate se takodje pamte u bazi podataka u odgovarajucoj tabeli.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class KreirajBaletskogIgracaSO extends ApstraktnaSO {
	
	/**
	 * Promenljiva u kojoj se cuva indeks kreiranog baletskog igraca.
	 */
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
    /**
     * Metoda u kojoj se poziva operacija za kreiranje sloga u bazi podataka i izvrsava odgovarajuci
     * upit nad bazom podataka. Kreira se slog za baletskog igraca i slogovi za njegove uplate.
     * 
     * Rezultat upita je indeks kreiranog baletskog igraca.
     * 
     */
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
