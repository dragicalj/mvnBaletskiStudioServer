package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija svih koreografa koji postoje
 * u baletskom studiju. Iz baze podataka ucitavaju se podaci o koreografima i rezultat 
 * operacije je lista objekata klase Koreograf.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiSveKoreografeSO extends ApstraktnaSO{
	/**
     * Promenljiva u kojoj ce se cuvati koreografi kao rezultat upita.
     */
    private List<ApstraktniDomenskiObjekat> listaKoreografa;
    
    public VratiSveKoreografeSO() {
    	super();
    }
    
    public VratiSveKoreografeSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
        
    }

    /**
     * Metoda u kojoj se poziva operacija za vracanje svih koreografa iz baze podataka.
     * Rezultat operacije su koreografi sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaKoreografa = repository.vratiSve((ApstraktniDomenskiObjekat) new Koreograf());
  
    }

    public List<ApstraktniDomenskiObjekat> getListaKoreografa() throws Exception{
        return listaKoreografa;
    }
    
    
}
