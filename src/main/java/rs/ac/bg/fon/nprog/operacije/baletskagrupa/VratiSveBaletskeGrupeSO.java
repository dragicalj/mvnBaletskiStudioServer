package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.repository.Repository;

public class VratiSveBaletskeGrupeSO extends ApstraktnaSO{
    
    private List<ApstraktniDomenskiObjekat> listaBaletskihGrupa;
    
    public VratiSveBaletskeGrupeSO() {
    	super();
    }
    
    public VratiSveBaletskeGrupeSO(Repository repository) {
		super(repository);
	}
    
    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        listaBaletskihGrupa=repository.vratiSve((ApstraktniDomenskiObjekat)new BaletskaGrupa());
        
        for (ApstraktniDomenskiObjekat ado : listaBaletskihGrupa) {
            BaletskaGrupa baletskaGrupa = (BaletskaGrupa) ado;
            
            baletskaGrupa.setKoreograf((Koreograf) repository.vratiPoId((ApstraktniDomenskiObjekat) baletskaGrupa.getKoreograf()));
            
            
            
        }
    }

    public List<ApstraktniDomenskiObjekat> getListaBaletskihGrupa() {
        return listaBaletskihGrupa;
    }
    
}
