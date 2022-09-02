package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import java.util.ArrayList;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za pamcenje nastupa baletske grupe. Baletska grupa 
 * sadrzi listu nastupa.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class ZapamtiNastupeBaletskeGrupeSO extends ApstraktnaSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof BaletskaGrupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase BaletskaGrupa!");
        }
        
    }
    /**
     * Metoda u kojoj se pamete nastupi konkretne baletske grupe u bazi podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        BaletskaGrupa baletskaGrupa= (BaletskaGrupa) param;
        ArrayList<ApstraktniDomenskiObjekat> listaIzBaze=(ArrayList<ApstraktniDomenskiObjekat>) repository.pronadji(baletskaGrupa.getListaNastupa().get(0));
        for (BaletskaGrupaNastup grupaNastup : baletskaGrupa.getListaNastupa()) {
            if(!(repository.daLiPostoji((BaletskaGrupaNastup)grupaNastup))){
                
                grupaNastup.setBaletskaGrupaId(baletskaGrupa);
                repository.kreirajSlog((BaletskaGrupaNastup) grupaNastup);
            }
        }
        for (ApstraktniDomenskiObjekat ado : listaIzBaze) {
            if (!baletskaGrupa.getListaNastupa().contains(ado)) {
                repository.obrisi(ado);
            }
        }
    }
    
}