package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za promenu podataka postojeceg baletskog igraca. 
 * Operacija se izvrsava pozivom odgovarajuceg upita nad bazom podataka i nema nikavu povratnu vrednost.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class PromeniPodatkeBaletskogIgracaSO extends ApstraktnaSO{
	
	public PromeniPodatkeBaletskogIgracaSO() {
		super();
	}
	
	public PromeniPodatkeBaletskogIgracaSO(Repository repository) {
		super(repository);
	}
	
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

