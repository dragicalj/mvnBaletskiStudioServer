package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za pretragu koreografa po odredjenim uslovima.
 * Iz baze podataka ucitavaju se podaci o koreografima koji ispunjavaju  uslove i rezultat 
 * operacije je lista objekata klase Koreograf.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class PronadjiKoreografeSO extends ApstraktnaSO{
    
	/**
     * Promenljiva u kojoj ce se cuvati koreografi kao rezultat upita.
     */
    private List<ApstraktniDomenskiObjekat> listaKoreografa;
    
    public PronadjiKoreografeSO() {
    	super();
    }
    
    public PronadjiKoreografeSO(Repository repository) {
		super(repository);
	}

    @Override
    protected void precondition(Object param) throws Exception {
    }
    
    /**
     * Metoda u kojoj se poziva operacija za pretragu koreografa iz baze podataka.
     * Rezultat operacije su koreografi koji ispunjavaju uslov.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaKoreografa=repository.pronadji((ApstraktniDomenskiObjekat)param);
    }

    public List<ApstraktniDomenskiObjekat> getListaKoreografa() {
        return listaKoreografa;
    }
    
}

