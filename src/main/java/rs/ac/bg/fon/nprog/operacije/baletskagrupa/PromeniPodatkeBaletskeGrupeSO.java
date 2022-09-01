package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class PromeniPodatkeBaletskeGrupeSO extends ApstraktnaSO{
	
	public PromeniPodatkeBaletskeGrupeSO() {
		super();
	}
	
	public PromeniPodatkeBaletskeGrupeSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskaGrupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskaGrupa!");
        }
        BaletskaGrupa baletskaGrupa = (BaletskaGrupa) param;
        if (baletskaGrupa.getKapacitet() <= 0) {
            throw new Exception("Kapacitet grupe mora biti veci od 0!");

        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       repository.promeni((ApstraktniDomenskiObjekat)param);

    }
    
}
