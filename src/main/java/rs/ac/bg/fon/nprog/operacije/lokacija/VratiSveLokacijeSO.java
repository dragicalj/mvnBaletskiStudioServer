package rs.ac.bg.fon.nprog.operacije.lokacija;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija svih lokacija na kojima se mogu 
 * odrzavati nastupi baletskog studija. Iz baze podataka ucitavaju se podaci o lokacijama i rezultat 
 * operacije je lista objekata klase Lokacija.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiSveLokacijeSO extends ApstraktnaSO{
    
	/**
     * Promenljiva u kojoj ce se cuvati lokacije kao rezultat operacije.
     */
    private List<ApstraktniDomenskiObjekat> listaLokacija;

    public VratiSveLokacijeSO() {
    	super();
    }
    
    public VratiSveLokacijeSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    /**
     * Metoda u kojoj se poziva operacija za vracanje svih lokacija iz baze podataka.
     * Rezultat operacije su lokacije sa svim podacima.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        listaLokacija= repository.vratiSve((ApstraktniDomenskiObjekat) new Lokacija());

    }

    public List<ApstraktniDomenskiObjekat> getListaLokacija() {
        return listaLokacija;
    }
    
}
