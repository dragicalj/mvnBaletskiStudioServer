package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.BaletskaGrupa;
import rs.ac.bg.fon.nprog.domen.BaletskaGrupaNastup;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class KreirajBaletskuGrupuSOTest extends ApstraktnaSOTest{
	
	KreirajBaletskuGrupuSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new KreirajBaletskuGrupuSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPreconditionInstancaKlase() {
		BaletskaGrupa bg=new BaletskaGrupa();
		assertTrue(bg instanceof BaletskaGrupa);
	}
	
	@Test
	void testPreconditionDatumRodjenja() {
		BaletskaGrupa bg=new BaletskaGrupa();
		assertThrows(java.lang.Exception.class, () -> bg.setKapacitet(-5));		
	}

	@Test
	void testExecuteOperation() {
		try {
			List<BaletskaGrupaNastup> listaNastupa=new ArrayList<BaletskaGrupaNastup>();
			Koreograf koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );
			BaletskaGrupa baletskaGrupa = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf, listaNastupa);

			when(repository.kreirajSlog(baletskaGrupa)).thenReturn(1l);
			operacija.execute(baletskaGrupa);
			verify(repository, times(1)).kreirajSlog(baletskaGrupa);
			assertEquals(1l, operacija.indeks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
