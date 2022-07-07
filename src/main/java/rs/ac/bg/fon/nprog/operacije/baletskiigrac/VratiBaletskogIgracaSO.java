package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class VratiBaletskogIgracaSO extends ApstraktnaSO{
    
    private BaletskiIgrac baletskiIgrac;

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        baletskiIgrac=(BaletskiIgrac) repository.vratiPoId((ApstraktniDomenskiObjekat)param);
        
        Uplata uplata=new Uplata();
        uplata.setBaletskiIgrac(baletskiIgrac);
        
        List<ApstraktniDomenskiObjekat> listaUplata=repository.vratiPoUslovu(uplata);
        //System.out.println(listaUplata);
        ArrayList<Uplata> uplate=new ArrayList<>();
        for (ApstraktniDomenskiObjekat apstraktniDomenskiObjekat : listaUplata) {
            uplate.add((Uplata)apstraktniDomenskiObjekat);
        }
        //System.out.println(uplate);
        baletskiIgrac.setListaUplata(uplate);

    }

    public BaletskiIgrac getBaletskiIgrac() {
        return baletskiIgrac;
    }
    
}
