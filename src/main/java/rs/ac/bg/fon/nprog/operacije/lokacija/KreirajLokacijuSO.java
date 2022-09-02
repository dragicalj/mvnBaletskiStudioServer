package rs.ac.bg.fon.nprog.operacije.lokacija;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za kreiranje lokacije. Lokacija se pamti
 * u bazi podataka, a rezultat operacije je indeks kreirane lokacije.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class KreirajLokacijuSO extends ApstraktnaSO{
	/**
	 * Promenljiva u kojoj se cuva indeks kreirane lokacije.
	 */
    long indeks=-1;

    public KreirajLokacijuSO() {
        super();
    }

    public KreirajLokacijuSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }
    /**
     * Metoda u kojoj se poziva operacija za kreiranje sloga u bazi podataka i izvrsava odgovarajuci
     * upit nad bazom podataka.
     * 
     * Rezultat upita je indeks kreirane lokacije.
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

