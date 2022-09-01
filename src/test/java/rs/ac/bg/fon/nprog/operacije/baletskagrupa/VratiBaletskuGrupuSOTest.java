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
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.domen.TipNastupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiBaletskuGrupuSOTest extends ApstraktnaSOTest{
	
	VratiBaletskuGrupuSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiBaletskuGrupuSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}


	@Test
	void testExecuteOperation() {
		try {
			List<BaletskaGrupaNastup> listaNastupa=new ArrayList<BaletskaGrupaNastup>();
			Koreograf koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );
			BaletskaGrupa baletskaGrupa = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf, listaNastupa);
			
			when(repository.vratiPoId(baletskaGrupa)).thenReturn(baletskaGrupa);
			

			Lokacija l=new Lokacija(1l, "Vrsac", "Hala sportova", "111");
			Nastup nastup1=new Nastup(1l, new Date(2022, 10, 1), TipNastupa.TAKMICENJE, l);
			Nastup nastup2=new Nastup(2l, new Date(2022, 10, 1), TipNastupa.TAKMICENJE, l);
			BaletskaGrupaNastup grupaNastup1=new BaletskaGrupaNastup(baletskaGrupa, nastup1);
			BaletskaGrupaNastup grupaNastup2=new BaletskaGrupaNastup(baletskaGrupa, nastup2);
			
			List<ApstraktniDomenskiObjekat> lista=new ArrayList<ApstraktniDomenskiObjekat>();
			lista.add(grupaNastup1);
			lista.add(grupaNastup2);
			
			when(repository.vratiPoUslovu(grupaNastup1)).thenReturn(lista);
			
			when(repository.vratiPoId(nastup1)).thenReturn(nastup1);
			when(repository.vratiPoId(nastup2)).thenReturn(nastup2);
			
			operacija.execute(baletskaGrupa);
			
			listaNastupa.add(grupaNastup1);
			listaNastupa.add(grupaNastup2);
			
			baletskaGrupa.setListaNastupa(listaNastupa);
			
		
			assertEquals(baletskaGrupa, operacija.getBaletskaGrupa());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
