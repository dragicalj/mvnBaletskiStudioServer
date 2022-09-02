package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za pretragu baletskih igraca po odredjenim uslovima.
 * Iz baze podataka ucitavaju se podaci o baletskim  igracima koji ispunjavaju  uslove i rezultat 
 * operacije je lista objekata klase BaletskiIgrac.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class PronadjiBaletskeIgraceSO extends ApstraktnaSO {
	
	/**
     * Promenljiva u kojoj ce se cuvati baletski igraci kao rezultat upita.
     */
    private List<ApstraktniDomenskiObjekat> listaBaletskihIgraca;
    
    public PronadjiBaletskeIgraceSO() {
    	super();
    }
    
    public PronadjiBaletskeIgraceSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }
    
    /**
     * Metoda u kojoj se poziva operacija za pretragu baletskih igraca iz baze podataka.
     * Rezultat operacije su baletski igraci koji ispunjavaju uslov.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaBaletskihIgraca = repository.pronadji((ApstraktniDomenskiObjekat) param);
    }

    public List<ApstraktniDomenskiObjekat> getListaBaletskihIgraca() {
        return listaBaletskihIgraca;
    }

}
