package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za prikaz informacija konkretnog baletskog igraca 
 * i njegovih uplata. Iz baze podataka uzimaju se podaci potrebnog baletskog igraca i rezultat 
 * operacije je objekat klase BaletskiIgrac.
 * 
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class VratiBaletskogIgracaSO extends ApstraktnaSO{
	/**
     * Promenljiva u kojoj ce se cuvati odgovarajuci baletski igrac kao rezultat upita.
     */
    private BaletskiIgrac baletskiIgrac;
    
    public VratiBaletskogIgracaSO() {
    	super();
    }
    
    public VratiBaletskogIgracaSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }
    /**
     * Metoda u kojoj se poziva operacija za vracanje konkretnog baletskog igraca po njegovom identifikatoru.
     * Baletski igrac ima svoje uplate koje se takodje ucitavaju.
     * Rezultat operacije je baletski igrac sa svim podacima i listom njegovih uplata clanarina.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        baletskiIgrac=(BaletskiIgrac) repository.vratiPoId((ApstraktniDomenskiObjekat)param);
        
        Uplata uplata=new Uplata();
        uplata.setBaletskiIgrac(baletskiIgrac);
        
        List<ApstraktniDomenskiObjekat> listaUplata=repository.vratiPoUslovu(uplata);
        System.out.println(listaUplata);
        ArrayList<Uplata> uplate=new ArrayList<>();
        for (ApstraktniDomenskiObjekat apstraktniDomenskiObjekat : listaUplata) {
            uplate.add((Uplata)apstraktniDomenskiObjekat);
        }
        System.out.println(uplate);
        baletskiIgrac.setListaUplata(uplate);

    }

    public BaletskiIgrac getBaletskiIgrac() {
        return baletskiIgrac;
    }
    
}
