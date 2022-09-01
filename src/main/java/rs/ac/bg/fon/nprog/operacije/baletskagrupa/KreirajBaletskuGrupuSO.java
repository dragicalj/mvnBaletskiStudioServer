package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;
/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za kreiranje baletske grupe. Baletska grupa se pamti
 * u bazi podataka, a rezultat operacije je indeks kreirane baletske grupe.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class KreirajBaletskuGrupuSO extends ApstraktnaSO {
	/**
	 * Promenljiva u kojoj se cuva indeks kreirane grupe.
	 */
    long indeks = -1;

    public KreirajBaletskuGrupuSO() {
        super();
    }
   
    public KreirajBaletskuGrupuSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskaGrupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskaGrupa!");
        }
        BaletskaGrupa baletskaGrupa = (BaletskaGrupa) param;
        if (baletskaGrupa.getKapacitet() <= 0) {
            throw new Exception("Kapacitet grupe mora biti veci od 0!");

        }
    }
    /**
     * Metoda u kojoj se poziva operacija za kreiranje sloga u bazi podataka i izvrsava odgovarajuci
     * upit nad bazom podataka.
     * 
     * Rezultat upita je indeks kreirane baletske grupe.
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        indeks = repository.kreirajSlog((ApstraktniDomenskiObjekat) param);

    }

    public long getIndeks() {
        return indeks;
    }

}
