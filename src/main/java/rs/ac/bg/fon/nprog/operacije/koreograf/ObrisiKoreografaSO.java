package rs.ac.bg.fon.nprog.operacije.koreograf;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za brisanje koreografa. Operacija
 * se izvrsava pozivom odgovarajuceg upita nad bazom podataka i nema nikavu povratnu vrednost.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class ObrisiKoreografaSO extends ApstraktnaSO{
	
	public ObrisiKoreografaSO() {
		super();
	}
	
	public ObrisiKoreografaSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
    }

    /**
     * Metoda u kojoj se poziva operacija za brisanje odgovarajuceg sloga u bazi podataka i izvrsava 
     * odgovarajuci upit nad bazom podataka.
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.obrisi((ApstraktniDomenskiObjekat)param);
    }
    
}

