package rs.ac.bg.fon.nprog.operacije.administrator;

import rs.ac.bg.fon.nprog.domen.Administrator;
import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

/**
 * 
 * Klasa koja predstavlja sistemsku operaciju za logovanje administratora na aplikaciju. Vrsi se provera
 * da li administrator postoji u bazi. Ako je login uspesan dobija se objekat klase administrator.
 * 
 * Klasa nasledjuje klasu koja predstavlja apstraktnu sistemsku operaciju.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public class LoginSO extends ApstraktnaSO{
	/**
	 * Apstraktni domenski objekat u kojem ce se cuvati rezultat sistemske operacije.
	 */
	ApstraktniDomenskiObjekat ado=null;

    public LoginSO() {
        super();
    }
    
    @Override
    protected void precondition(Object param) throws Exception {
        if (!(param instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }
    /**
     * Metoda u kojoj se poziva se operacija nad repozitorijumom i izvrsava upit nad bazom podataka.
     * 
     * Rezultat upita se pamti u apstraktnom domenskom objektu.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        ado=repository.vratiPoId((ApstraktniDomenskiObjekat)param);
    }
    /**
     * Vraca apstraktni domenski objekat.
     * 
     * @return apstraktni domenski objekat
     */
    public ApstraktniDomenskiObjekat getAdo() {
        return ado;
    }
     
}
