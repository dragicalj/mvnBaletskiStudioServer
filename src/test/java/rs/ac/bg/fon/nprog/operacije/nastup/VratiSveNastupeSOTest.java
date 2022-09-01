package rs.ac.bg.fon.nprog.operacije.nastup;

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
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiSveNastupeSOTest extends ApstraktnaSOTest {
	
	VratiSveNastupeSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiSveNastupeSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	@Test
	void testExecuteOperation() {
		try {
			Lokacija lokacija=new Lokacija(1l, "Pozarevac", "Dom kulture", "glavna bina");
			
			ApstraktniDomenskiObjekat nastup1=new Nastup(1l, new Date(2022, 10, 1), TipNastupa.KONCERT, lokacija);
			ApstraktniDomenskiObjekat nastup2=new Nastup(2l, new Date(2022, 11, 2), TipNastupa.TAKMICENJE, lokacija);
			
			List<ApstraktniDomenskiObjekat> listaNastupa=new ArrayList<ApstraktniDomenskiObjekat>();
			
			listaNastupa.add(nastup1);
			listaNastupa.add(nastup2);
			
			when(repository.vratiSve(any())).thenReturn(listaNastupa);
			when(repository.vratiPoId(nastup1)).thenReturn(lokacija);
			when(repository.vratiPoId(nastup2)).thenReturn(lokacija);

			operacija.execute(nastup1);
			verify(repository, times(1)).vratiSve(any());
			assertEquals(listaNastupa, operacija.getListaNastupa());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
