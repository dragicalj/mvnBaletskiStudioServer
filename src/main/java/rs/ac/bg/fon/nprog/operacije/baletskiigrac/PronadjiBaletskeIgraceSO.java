package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class PronadjiBaletskeIgraceSO extends ApstraktnaSO {

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

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaBaletskihIgraca = repository.pronadji((ApstraktniDomenskiObjekat) param);
    }

    public List<ApstraktniDomenskiObjekat> getListaBaletskihIgraca() {
        return listaBaletskihIgraca;
    }

}
