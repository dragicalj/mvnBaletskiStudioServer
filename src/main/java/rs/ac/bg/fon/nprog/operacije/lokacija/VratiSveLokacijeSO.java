package rs.ac.bg.fon.nprog.operacije.lokacija;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class VratiSveLokacijeSO extends ApstraktnaSO{
    
    private List<ApstraktniDomenskiObjekat> listaLokacija;


    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaLokacija= repository.vratiSve((ApstraktniDomenskiObjekat) new Lokacija());

    }

    public List<ApstraktniDomenskiObjekat> getListaLokacija() {
        return listaLokacija;
    }
    
}
