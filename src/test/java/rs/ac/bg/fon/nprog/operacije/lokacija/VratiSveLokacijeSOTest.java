package rs.ac.bg.fon.nprog.operacije.lokacija;

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
import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.domen.Lokacija;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSO;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiSveLokacijeSOTest extends ApstraktnaSOTest{
	
	VratiSveLokacijeSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiSveLokacijeSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}

	
	@Test
	void testExecuteOperation() {
		try {
			Lokacija lokacija1=new Lokacija(1l, "Pozarevac", "Dom kulture", "glavna bina");
			Lokacija lokacija2=new Lokacija(2l, "Vrsac", "Hala sportova", "velika sala");
			List<ApstraktniDomenskiObjekat> listaLokacija=new ArrayList<ApstraktniDomenskiObjekat>();
			
			listaLokacija.add(lokacija1);
			listaLokacija.add(lokacija2);
			
			when(repository.vratiSve(any())).thenReturn(listaLokacija);
			operacija.execute(lokacija1);
			verify(repository, times(1)).vratiSve(any());
			assertEquals(listaLokacija, operacija.getListaLokacija());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
