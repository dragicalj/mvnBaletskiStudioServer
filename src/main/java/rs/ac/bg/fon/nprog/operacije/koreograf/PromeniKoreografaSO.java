package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za promenu podataka postojeceg koreografa. Operacija
 * se izvrsava pozivom odgovarajuceg upita nad bazom podataka i nema nikavu povratnu vrednost.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class PromeniKoreografaSO extends ApstraktnaSO{
	
	public PromeniKoreografaSO() {
		super();
	}
	
	public PromeniKoreografaSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Koreograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koreograf!");
        }
        Koreograf koreograf=(Koreograf) param;

        if(koreograf.getDatumRodjenja().after(new Date())
                || koreograf.getDatumRodjenja().equals(new Date())){
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
        }
    }
    /**
     * Metoda u kojoj se poziva operacija za promenu odgovarajuceg sloga u bazi podataka i izvrsava 
     * odgovarajuci upit nad bazom podataka.
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.promeni((ApstraktniDomenskiObjekat) param);
    }
    
}

