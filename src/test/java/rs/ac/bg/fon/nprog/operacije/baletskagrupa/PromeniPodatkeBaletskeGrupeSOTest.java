package rs.ac.bg.fon.nprog.operacije.baletskagrupa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

class PromeniPodatkeBaletskeGrupeSOTest extends ApstraktnaSOTest {
	
	PromeniPodatkeBaletskeGrupeSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new PromeniPodatkeBaletskeGrupeSO(repository);
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
			ApstraktniDomenskiObjekat baletskaGrupa = new BaletskaGrupa(1l, "kolibri",TipGrupe.Mini, new Date(1, 5, 2015), 20, koreograf, listaNastupa);
			
			operacija.execute(baletskaGrupa);
			verify(repository, times(1)).promeni(baletskaGrupa);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
