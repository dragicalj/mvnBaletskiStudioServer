package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.sql.Date;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class KreirajKoreografaSO extends ApstraktnaSO{

    long indeks=-1;

    public KreirajKoreografaSO() {
        super();
    }
   
    
    public KreirajKoreografaSO(Repository repository) {
		super(repository);
	}


	@Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Koreograf)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Koreograf!");
        }
        Koreograf koreograf=(Koreograf) param;

        if(koreograf.getDatumRodjenja().after(new java.util.Date())
                || koreograf.getDatumRodjenja().equals(new java.util.Date())){
            throw new Exception("Datum rodjenja mora biti pre danasnjeg datuma!");
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
