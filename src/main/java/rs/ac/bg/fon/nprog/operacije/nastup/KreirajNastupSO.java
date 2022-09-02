package rs.ac.bg.fon.nprog.operacije.nastup;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za kreiranje nastupa. Nastup se pamti
 * u bazi podataka, a rezultat operacije je indeks kreiranog nastupa.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class KreirajNastupSO extends ApstraktnaSO{
	/**
	 * Promenljiva u kojoj se cuva indeks kreiranog nastupa.
	 */
    long indeks=-1;

    public KreirajNastupSO() {
        super();
    }
    
    public KreirajNastupSO(Repository repository) {
		super(repository);
	}

    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Nastup)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Nastup!");
        }
        Nastup nastup=(Nastup) param;

        if(nastup.getDatumVremeNastupa().before(new Date())
                || nastup.getDatumVremeNastupa().equals(new Date())){
            throw new Exception("Datum nastupa mora biti posle danasnjeg datuma!");
        }
    }
    /**
     * Metoda u kojoj se poziva operacija za kreiranje sloga u bazi podataka i izvrsava odgovarajuci
     * upit nad bazom podataka.
     * 
     * Rezultat upita je indeks kreiranog nastupa.
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        indeks=repository.kreirajSlog((ApstraktniDomenskiObjekat)param);

    }

    public long getIndeks() {
        return indeks;
    }
    
}
