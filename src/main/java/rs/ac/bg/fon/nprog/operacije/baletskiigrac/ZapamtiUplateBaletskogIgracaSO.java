package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import java.util.ArrayList;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za pamcenje uplatabaletskog igraca. Operacija
 * se izvrsava pozivom odgovarajuceg upita nad bazom podataka i nema nikavu povratnu vrednost.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
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
    /**
     * Metoda u kojoj se poziva operacija za promenu odgovarajuceg sloga u bazi podataka i izvrsava 
     * odgovarajuci upit nad bazom podataka. PRoverava se da li isti slog postoji u bazi, ako ne postoji
     * kreira se. Takodje, vrsi se provera da li je treba neki slog obrisati.
     * 
     */
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

