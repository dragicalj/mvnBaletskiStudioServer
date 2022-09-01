package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiSveBaletskeGrupeSOTest extends ApstraktnaSOTest{
	
	VratiSveBaletskeGrupeSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiSveBaletskeGrupeSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}


	@Test
	void testExecuteOperation() {
		try {
			List<BaletskaGrupaNastup> listaNastupa=new ArrayList<BaletskaGrupaNastup>();
			Koreograf koreograf1 = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );
			ApstraktniDomenskiObjekat baletskaGrupa1 = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf1, listaNastupa);
			
			Koreograf koreograf2 = new Koreograf(2l, "Lena","Lubic",new Date(1, 2, 2000),"@","+38165822320","Moderan balet" );
			ApstraktniDomenskiObjekat baletskaGrupa2 = new BaletskaGrupa(2l, "miks",TipGrupe.Senior, new Date(1, 5, 2015), 20, koreograf2, listaNastupa);
			
			List<ApstraktniDomenskiObjekat> listaGrupa=new ArrayList<ApstraktniDomenskiObjekat>();
			
			listaGrupa.add(baletskaGrupa1);
			listaGrupa.add(baletskaGrupa2);
			
			when(repository.vratiSve(any())).thenReturn(listaGrupa);
			when(repository.vratiPoId(baletskaGrupa1)).thenReturn(koreograf1);
			when(repository.vratiPoId(baletskaGrupa2)).thenReturn(koreograf2);

			operacija.execute(baletskaGrupa1);
			verify(repository, times(1)).vratiSve(any());
			assertEquals(listaGrupa, operacija.getListaBaletskihGrupa());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
