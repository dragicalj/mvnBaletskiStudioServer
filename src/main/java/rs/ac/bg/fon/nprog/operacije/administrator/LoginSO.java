package rs.ac.bg.fon.nprog.operacije.administrator;

import rs.ac.bg.fon.nprog.domen.Administrator;
import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class LoginSO extends ApstraktnaSO{
	
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

    @Override
    protected void executeOperation(Object param) throws Exception {
        ado=repository.vratiPoId((ApstraktniDomenskiObjekat)param);
    }

    public ApstraktniDomenskiObjekat getAdo() {
        return ado;
    }
     
}
