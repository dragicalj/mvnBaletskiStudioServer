package rs.ac.bg.fon.nprog.operacije.baletskiigrac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.domen.Uplata;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class KreirajBaletskogIgracaSOTest extends ApstraktnaSOTest{
	
	KreirajBaletskogIgracaSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new KreirajBaletskogIgracaSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPreconditionInstancaKlase() {
		BaletskiIgrac igrac=new BaletskiIgrac();
		assertTrue(igrac instanceof BaletskiIgrac);
	}
	
	@Test
	void testPreconditionDatumRodjenja() {
		BaletskiIgrac igrac=new BaletskiIgrac();
		assertThrows(java.lang.Exception.class, () -> igrac.setDatumRodjenja(new Date(2225, 1, 4)));		
	}

	@Test
	void testExecuteOperation() {
		try {
			List<BaletskaGrupaNastup> listaNastupa=new ArrayList<BaletskaGrupaNastup>();
			Koreograf koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"mejl@gmail.com","+38165822320","Klasican balet" );
			BaletskaGrupa baletskaGrupa = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf, listaNastupa);
			List<Uplata> listaUplata=new ArrayList<Uplata>();
			BaletskiIgrac baletskiIgrac=new BaletskiIgrac(1l, "Milica", "Milic", new Date(1, 2, 2006), "mejl@gmail.com", "+38165822320", "+38165822000", new Date(1, 2, 2016), new BigDecimal(2000), baletskaGrupa, listaUplata);
			
			Uplata uplata1=new Uplata(baletskiIgrac, 1, new BigDecimal(2000), new Date(1,7,2022), "jul", "2022");
			Uplata uplata2=new Uplata(baletskiIgrac, 2, new BigDecimal(2000), new Date(1,7,2022), "avgust", "2022");

			listaUplata.add(uplata1);
			listaUplata.add(uplata2);

			when(repository.kreirajSlog(baletskiIgrac)).thenReturn(1l);
		
			operacija.execute(baletskiIgrac);
			verify(repository, times(1)).kreirajSlog(uplata1);
			verify(repository, times(1)).kreirajSlog(uplata2);

			assertEquals(1l, operacija.indeks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
