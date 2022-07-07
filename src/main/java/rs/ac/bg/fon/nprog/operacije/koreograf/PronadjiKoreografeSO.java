package rs.ac.bg.fon.nprog.operacije.koreograf;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class PronadjiKoreografeSO extends ApstraktnaSO{
    
    private List<ApstraktniDomenskiObjekat> listaKoreografa;

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

