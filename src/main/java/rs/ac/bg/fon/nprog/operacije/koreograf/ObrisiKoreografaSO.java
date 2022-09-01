package rs.ac.bg.fon.nprog.operacije.koreograf;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class ObrisiKoreografaSO extends ApstraktnaSO{
	
	public ObrisiKoreografaSO() {
		super();
	}
	
	public ObrisiKoreografaSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.obrisi((ApstraktniDomenskiObjekat)param);
    }
    
}

