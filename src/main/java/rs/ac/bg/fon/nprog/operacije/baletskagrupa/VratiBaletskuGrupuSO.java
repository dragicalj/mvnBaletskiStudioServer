package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;

public class VratiBaletskuGrupuSO extends ApstraktnaSO{
    
    private BaletskaGrupa baletskaGrupa;

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        baletskaGrupa=(BaletskaGrupa) repository.vratiPoId((ApstraktniDomenskiObjekat)param);
        
        BaletskaGrupaNastup baletskaGrupaNastup=new BaletskaGrupaNastup();
        baletskaGrupaNastup.setBaletskaGrupaId(baletskaGrupa);
        
        List<ApstraktniDomenskiObjekat> listaNastupa=repository.vratiPoUslovu(baletskaGrupaNastup);

        for (ApstraktniDomenskiObjekat ado : listaNastupa) {
            BaletskaGrupaNastup grupaNastup=(BaletskaGrupaNastup)ado;
            Nastup nastup=(Nastup) repository.vratiPoId((ApstraktniDomenskiObjekat)grupaNastup.getNastupId());
            nastup.setLokacija((Lokacija) repository.vratiPoId((ApstraktniDomenskiObjekat)nastup.getLokacija()));
            grupaNastup.setNastupId(nastup);
            grupaNastup.setBaletskaGrupaId(baletskaGrupa);
        }
        //System.out.println(listaUplata);
        //System.out.println(listaNastupa+"iz baze");
        ArrayList<BaletskaGrupaNastup> nastupi=new ArrayList<>();
        for (ApstraktniDomenskiObjekat ado : listaNastupa) {
            
            nastupi.add((BaletskaGrupaNastup)ado);
        }
        System.out.println(nastupi);
        baletskaGrupa.setListaNastupa(nastupi);
    }

    public BaletskaGrupa getBaletskaGrupa() {
        return baletskaGrupa;
    }
    
    
}
