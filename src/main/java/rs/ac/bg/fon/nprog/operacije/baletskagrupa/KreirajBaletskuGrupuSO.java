package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class KreirajBaletskuGrupuSO extends ApstraktnaSO {

    long indeks = -1;

    public KreirajBaletskuGrupuSO() {
        super();
    }
    
    public KreirajBaletskuGrupuSO(Repository repository) {
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
        indeks = repository.kreirajSlog((ApstraktniDomenskiObjekat) param);

    }

    public long getIndeks() {
        return indeks;
    }

}
