package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija svih baletska grupa koje postoje
 * u baletskom studiju. Iz baze podataka ucitavaju se podaci o baletskim  grupama i rezultat 
 * operacije je lista objekata klase BaletskaGrupa.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiSveBaletskeGrupeSO extends ApstraktnaSO{
	/**
     * Promenljiva u kojoj ce se cuvati baletske grupe kao rezultat upita.
     */
    private List<ApstraktniDomenskiObjekat> listaBaletskihGrupa;
    
    public VratiSveBaletskeGrupeSO() {
    	super();
    }
    
    public VratiSveBaletskeGrupeSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }
    
    /**
     * Metoda u kojoj se poziva operacija za vracanje svih baletskih grupa iz baze podataka.
     * Rezultat operacije su baletske grupe sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaBaletskihGrupa=repository.vratiSve((ApstraktniDomenskiObjekat)new BaletskaGrupa());
        
        for (ApstraktniDomenskiObjekat ado : listaBaletskihGrupa) {
            BaletskaGrupa baletskaGrupa = (BaletskaGrupa) ado;
            
            baletskaGrupa.setKoreograf((Koreograf) repository.vratiPoId((ApstraktniDomenskiObjekat) baletskaGrupa.getKoreograf()));
            
            
            
        }
    }

    public List<ApstraktniDomenskiObjekat> getListaBaletskihGrupa() {
        return listaBaletskihGrupa;
    }
    
}
