package rs.ac.bg.fon.nprog.operacije.nastup;

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
import rs.ac.bg.fon.nprog.domen.BaletskiIgrac;
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.domen.Nastup;
import rs.ac.bg.fon.nprog.domen.TipGrupe;
import rs.ac.bg.fon.nprog.domen.TipNastupa;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class KreirajNastupSOTest extends ApstraktnaSOTest {
	
	KreirajNastupSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new KreirajNastupSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPreconditionInstance() {
		Nastup n=new Nastup();
		assertTrue(n instanceof Nastup);
	}
	
	@Test
	void testPreconditionDatumNastupa() {
		Nastup nastup=new Nastup();
		assertThrows(java.lang.Exception.class, () -> nastup.setDatumVremeNastupa(new Date(1, 1, 4)));		
	}

	@Test
	void testExecuteOperation() {
		try {
			Lokacija lokacija=new Lokacija(1l, "Pozarevac", "Dom kulture", "glavna bina");
			Nastup nastup=new Nastup(1l, new Date(2022, 10, 1), TipNastupa.KONCERT, lokacija);

			when(repository.kreirajSlog(nastup)).thenReturn(1l);
			operacija.execute(nastup);
			verify(repository, times(1)).kreirajSlog(nastup);
			assertEquals(1l, operacija.indeks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
