package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class VratiSveBaletskeIgraceSO extends ApstraktnaSO{
    
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
