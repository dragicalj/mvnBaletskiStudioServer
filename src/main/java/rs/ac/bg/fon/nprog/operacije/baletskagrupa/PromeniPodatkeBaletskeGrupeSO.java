package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;
/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za promenu podataka postojece baletske grupe. Operacija
 * se izvrsava pozivom odgovarajuceg upita nad bazom podataka i nema nikavu povratnu vrednost.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class PromeniPodatkeBaletskeGrupeSO extends ApstraktnaSO{
	
	public PromeniPodatkeBaletskeGrupeSO() {
		super();
	}
	
	public PromeniPodatkeBaletskeGrupeSO(Repository repository) {
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
     * Metoda u kojoj se poziva operacija za promenu odgovarajuceg sloga u bazi podataka i izvrsava 
     * odgovarajuci upit nad bazom podataka.
     * 
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.promeni((ApstraktniDomenskiObjekat)param);

    }
    
}
