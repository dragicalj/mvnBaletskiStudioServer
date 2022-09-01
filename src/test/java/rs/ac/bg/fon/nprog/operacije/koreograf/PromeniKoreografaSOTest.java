package rs.ac.bg.fon.nprog.operacije.koreograf;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class PromeniKoreografaSOTest extends ApstraktnaSOTest{
	
	PromeniKoreografaSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new PromeniKoreografaSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPreconditionInstancaKlase() {
		Koreograf k=new Koreograf();
		assertTrue(k instanceof Koreograf);
	}
	
	@Test
	void testPreconditionDatumRodjenja() {
		Koreograf k=new Koreograf();
		assertThrows(java.lang.Exception.class, () -> k.setDatumRodjenja(new Date(2225, 1, 4)));		
	}

	@Test
	void testExecuteOperation() {
		try {
			ApstraktniDomenskiObjekat koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );

			operacija.execute(koreograf);
			verify(repository, times(1)).promeni(koreograf);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
