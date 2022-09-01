package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.domen.TipNastupa;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiBaletskogIgracaSOTest extends ApstraktnaSOTest{
	
	VratiBaletskogIgracaSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiBaletskogIgracaSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPrecondition() {
		fail("Not yet implemented");
	}

	@Test
	void testExecuteOperation() {
		try {
			List<BaletskaGrupaNastup> listaNastupa=new ArrayList<BaletskaGrupaNastup>();
			Koreograf koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"mejl@gmail.com","+38165822320","Klasican balet" );
			BaletskaGrupa baletskaGrupa = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf, listaNastupa);
			List<Uplata> listaUplata=new ArrayList<Uplata>();
			Uplata uplata=new Uplata(null, 1, new BigDecimal(2000), new Date(1,7,2022), "jul", "2022");
			listaUplata.add(uplata);
			BaletskiIgrac baletskiIgrac=new BaletskiIgrac(1l, "Milica", "Milic", new Date(1, 2, 2006), "mejl@gmail.com", "+38165822320", "+38165822000", new Date(1, 2, 2016), new BigDecimal(2000), baletskaGrupa, listaUplata);
			baletskiIgrac.getListaUplata().get(0).setBaletskiIgrac(baletskiIgrac);
			when(repository.vratiPoId(baletskiIgrac)).thenReturn(baletskiIgrac);
			
			Uplata uplata1=new Uplata(baletskiIgrac, 2, new BigDecimal(2000), new Date(1,7,2022), "jul", "2022");
			Uplata uplata2=new Uplata(baletskiIgrac, 3, new BigDecimal(2000), new Date(1,7,2022), "avgust", "2022");
			
			List<ApstraktniDomenskiObjekat> lista=new ArrayList<ApstraktniDomenskiObjekat>();
			lista.add(uplata);
			lista.add(uplata1);
			lista.add(uplata2);
			
			when(repository.vratiPoUslovu(baletskiIgrac.getListaUplata().get(0))).thenReturn(lista);
			
			operacija.execute(baletskiIgrac);
			
			listaUplata.add(uplata1);
			listaUplata.add(uplata2);
			
			baletskiIgrac.setListaUplata(listaUplata);
			
		
			assertEquals(baletskiIgrac, operacija.getBaletskiIgrac());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
