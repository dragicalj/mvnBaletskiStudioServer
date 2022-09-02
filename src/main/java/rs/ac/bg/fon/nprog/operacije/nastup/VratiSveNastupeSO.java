package rs.ac.bg.fon.nprog.operacije.nastup;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija svih nastupa koji su zakazani
 * baletskim grupama. Iz baze podataka ucitavaju se podaci o nastupima i rezultat 
 * operacije je lista objekata klase Nastup.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiSveNastupeSO extends ApstraktnaSO{
    
	/**
     * Promenljiva u kojoj ce se cuvati nastupi kao rezultat operacije.
     */
    private List<ApstraktniDomenskiObjekat> listaNastupa;
    
    public VratiSveNastupeSO() {
    	super();
    }
    
    public VratiSveNastupeSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    /**
     * Metoda u kojoj se poziva operacija za vracanje svih nastupa iz baze podataka.
     * Rezultat operacije su nastupi baletskih grupa sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaNastupa=repository.vratiSve((ApstraktniDomenskiObjekat)new Nastup());
        
        for (ApstraktniDomenskiObjekat ado : listaNastupa) {
            Nastup nastup=(Nastup) ado ;
            
            nastup.setLokacija((Lokacija) repository.vratiPoId((ApstraktniDomenskiObjekat)nastup.getLokacija()));
        }
        
    }

    public List<ApstraktniDomenskiObjekat> getListaNastupa() {
        return listaNastupa;
    }
    
}
