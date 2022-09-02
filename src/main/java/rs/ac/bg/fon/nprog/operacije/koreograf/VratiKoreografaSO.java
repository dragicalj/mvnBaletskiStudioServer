package rs.ac.bg.fon.nprog.operacije.koreograf;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija konkretnog koreografa. Iz baze
 * podataka uzimaju se podaci potrebnog koreografa i rezultat operacije je objekat klase Koreograf.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiKoreografaSO extends ApstraktnaSO{
	/**
     * Promenljiva u kojoj ce se cuvati odgovarajuci koreograf kao rezultat operacije.
     */
    private Koreograf koreograf;
    
    public VratiKoreografaSO() {
    	super();
    }
    
    public VratiKoreografaSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }
    
    /**
     * Metoda u kojoj se poziva operacija za vracanje konkretnog koreografa po njenogovom identifikatoru.
     * Rezultat operacije je koreograf sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
                //listaKoreografa = repository.vratiSve((ApstraktniDomenskiObjekat) new Koreograf());
        koreograf=(Koreograf) repository.vratiPoId((ApstraktniDomenskiObjekat)param);
    }

    public Koreograf getKoreograf() {
        return koreograf;
    }
     
}
