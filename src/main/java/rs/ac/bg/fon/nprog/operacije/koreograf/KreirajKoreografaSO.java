package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.sql.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;
/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za kreiranje koreografa. Koreograf se pamti
 * u bazi podataka, a rezultat operacije je indeks kreiranog koreografa.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class KreirajKoreografaSO extends ApstraktnaSO{
	/**
	 * Promenljiva u kojoj se cuva indeks kreiranog koreografa.
	 */
    long indeks=-1;

    public KreirajKoreografaSO() {
        super();
    }
   
    
    public KreirajKoreografaSO(Repository repository) {
		super(repository);
	}


	@Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Koreograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koreograf!");
        }
        Koreograf koreograf=(Koreograf) param;

        if(koreograf.getDatumRodjenja().after(new java.util.Date())
                || koreograf.getDatumRodjenja().equals(new java.util.Date())){
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
        }
        
    }
	/**
     * Metoda u kojoj se poziva operacija za kreiranje sloga u bazi podataka i izvrsava odgovarajuci
     * upit nad bazom podataka.
     * 
     * Rezultat upita je indeks kreiranog koreografa.
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
