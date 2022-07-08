package rs.ac.bg.fon.nprog.operacije.nastup;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class VratiSveNastupeSO extends ApstraktnaSO{
    
    private List<ApstraktniDomenskiObjekat> listaNastupa;

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaNastupa=repository.vratiSve((ApstraktniDomenskiObjekat)new Nastup());
        
        for (ApstraktniDomenskiObjekat ado : listaNastupa) {
            Nastup nastup=(Nastup) ado ;
            
            nastup.setLokacija((Lokacija) repository.vratiPoId((ApstraktniDomenskiObjekat)nastup.getLokacija()));
        }
        
    }

    public List<ApstraktniDomenskiObjekat> getListaNastupa() {
        return listaNastupa;
    }
    
}
