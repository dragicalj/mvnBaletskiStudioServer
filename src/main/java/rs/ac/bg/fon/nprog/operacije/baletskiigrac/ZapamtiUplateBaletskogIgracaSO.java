package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class ZapamtiUplateBaletskogIgracaSO extends ApstraktnaSO{
	
	public ZapamtiUplateBaletskogIgracaSO() {
		super();
	}
	
	public ZapamtiUplateBaletskogIgracaSO(Repository repository) {
		super(repository);
	}
	
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskiIgrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskiIgrac!");
        }
        BaletskiIgrac baletskiIgrac=(BaletskiIgrac) param;
       
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        BaletskiIgrac baletskiIgrac= (BaletskiIgrac) param;
        ArrayList<ApstraktniDomenskiObjekat> listaIzBaze=(ArrayList<ApstraktniDomenskiObjekat>) repository.pronadji(baletskiIgrac.getListaUplata().get(0));

        for (Uplata uplata : baletskiIgrac.getListaUplata()) {
            if(!(repository.daLiPostoji((Uplata)uplata))){
                uplata.setBaletskiIgrac(baletskiIgrac);
                repository.kreirajSlog((Uplata) uplata);
            }
        }
        for (ApstraktniDomenskiObjekat ado : listaIzBaze) {
            if (!baletskiIgrac.getListaUplata().contains(ado)) {
                repository.obrisi(ado);
            }
        }
    }
    
}

