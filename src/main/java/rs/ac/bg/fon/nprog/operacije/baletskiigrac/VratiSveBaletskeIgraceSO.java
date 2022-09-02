package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;
/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija svih baletskih igraca koji su clanovi
 * baletskog studija. Iz baze podataka ucitavaju se podaci o baletskim  igracima i rezultat 
 * operacije je lista objekata klase BaletskiIgrac.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiSveBaletskeIgraceSO extends ApstraktnaSO{
	/**
     * Promenljiva u kojoj ce se cuvati baletski igraci kao rezultat upita.
     */
    private List<ApstraktniDomenskiObjekat> listaBaletskihIgraca;
    
    public VratiSveBaletskeIgraceSO() {
    	super();
    }
    
    public VratiSveBaletskeIgraceSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }
    
    /**
     * Metoda u kojoj se poziva operacija za vracanje svih baletskih igraca iz baze podataka.
     * Rezultat operacije su baletski igraci sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaBaletskihIgraca=repository.vratiSve((ApstraktniDomenskiObjekat)new BaletskiIgrac());
        
        for (ApstraktniDomenskiObjekat ado : listaBaletskihIgraca) {
            BaletskiIgrac baletskiIgrac = (BaletskiIgrac) ado;
            
            baletskiIgrac.setBaletskaGrupa((BaletskaGrupa) repository.vratiPoId((ApstraktniDomenskiObjekat) baletskiIgrac.getBaletskaGrupa()));

        }
    }

    public List<ApstraktniDomenskiObjekat> getListaBaletskihIgraca() {
        return listaBaletskihIgraca;
    }
    
}
