package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class PromeniKoreografaSO extends ApstraktnaSO{
	
	public PromeniKoreografaSO() {
		super();
	}
	
	public PromeniKoreografaSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Koreograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koreograf!");
        }
        Koreograf koreograf=(Koreograf) param;

        if(koreograf.getDatumRodjenja().after(new Date())
                || koreograf.getDatumRodjenja().equals(new Date())){
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.promeni((ApstraktniDomenskiObjekat) param);
    }
    
}

