package rs.ac.bg.fon.nprog.repository;

import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;

public interface Repository {
	
	public ApstraktniDomenskiObjekat vratiPoId(ApstraktniDomenskiObjekat ado) throws Exception;

    public Long kreirajSlog(ApstraktniDomenskiObjekat ado) throws Exception;

    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat apstraktniDomenskiObjekat) throws Exception;

    public void promeni(ApstraktniDomenskiObjekat apstraktniDomenskiObjekat) throws Exception;

    public List<ApstraktniDomenskiObjekat> pronadji(ApstraktniDomenskiObjekat apstraktniDomenskiObjekat) throws Exception;
    public List<ApstraktniDomenskiObjekat> pronadji2(ApstraktniDomenskiObjekat apstraktniDomenskiObjekat) throws Exception;

    public List<ApstraktniDomenskiObjekat> vratiPoUslovu(ApstraktniDomenskiObjekat objekat) throws Exception;

    public boolean daLiPostoji(ApstraktniDomenskiObjekat ado) throws Exception;

    public void obrisi(ApstraktniDomenskiObjekat ado) throws Exception;
    
}
