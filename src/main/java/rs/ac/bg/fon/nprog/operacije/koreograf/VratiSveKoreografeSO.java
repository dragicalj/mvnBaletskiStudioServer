package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class VratiSveKoreografeSO extends ApstraktnaSO{
    
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

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaKoreografa = repository.vratiSve((ApstraktniDomenskiObjekat) new Koreograf());
  
    }

    public List<ApstraktniDomenskiObjekat> getListaKoreografa() throws Exception{
        return listaKoreografa;
    }
    
    
}
