package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class PronadjiKoreografeSO extends ApstraktnaSO{
    
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

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaKoreografa=repository.pronadji((ApstraktniDomenskiObjekat)param);
    }

    public List<ApstraktniDomenskiObjekat> getListaKoreografa() {
        return listaKoreografa;
    }
    
}

