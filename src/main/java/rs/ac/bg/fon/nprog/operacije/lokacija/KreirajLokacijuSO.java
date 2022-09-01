package rs.ac.bg.fon.nprog.operacije.lokacija;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class KreirajLokacijuSO extends ApstraktnaSO{
    
    long indeks=-1;

    public KreirajLokacijuSO() {
        super();
    }

    public KreirajLokacijuSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Lokacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Lokacija!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        indeks=repository.kreirajSlog((ApstraktniDomenskiObjekat)param);

    }

    public long getIndeks() {
        return indeks;
    }
    
}

