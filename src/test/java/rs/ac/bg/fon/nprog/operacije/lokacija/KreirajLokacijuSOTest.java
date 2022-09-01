package rs.ac.bg.fon.nprog.operacije.lokacija;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;
import rs.ac.bg.fon.nprog.repository.Repository;

class KreirajLokacijuSOTest extends ApstraktnaSOTest{
	
	KreirajLokacijuSO operacija;
	
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new KreirajLokacijuSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testPrecondition() {
		Lokacija l=new Lokacija();
		assertTrue(l instanceof Lokacija);
	}

	@Test
	void testExecuteOperation() {
		try {
			Lokacija lokacija=new Lokacija(1l, "Pozarevac", "Dom kulture", "glavna bina");
			
			when(repository.kreirajSlog(lokacija)).thenReturn(1l);
			operacija.execute(lokacija);
			verify(repository, times(1)).kreirajSlog(lokacija);
			assertEquals(1l, operacija.indeks);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
