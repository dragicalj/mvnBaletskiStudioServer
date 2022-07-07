package rs.ac.bg.fon.nprog.operacije.koreograf;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class VratiKoreografaSO extends ApstraktnaSO{
    
    private Koreograf koreograf;

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
                //listaKoreografa = repository.vratiSve((ApstraktniDomenskiObjekat) new Koreograf());
        koreograf=(Koreograf) repository.vratiPoId((ApstraktniDomenskiObjekat)param);
    }

    public Koreograf getKoreograf() {
        return koreograf;
    }
     
}
