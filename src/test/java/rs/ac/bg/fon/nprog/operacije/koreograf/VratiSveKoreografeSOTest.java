package rs.ac.bg.fon.nprog.operacije.koreograf;

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
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiSveKoreografeSOTest extends ApstraktnaSOTest{
	
	VratiSveKoreografeSO operacija;

	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiSveKoreografeSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}


	@Test
	void testExecuteOperation() {
		try {
			Koreograf koreograf1 = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );
			Koreograf koreograf2 = new Koreograf(2l, "Lena","Lubic",new Date(1, 2, 2000),"@","+38165822320","Moderan balet" );
			List<ApstraktniDomenskiObjekat> listaKoreografa=new ArrayList<ApstraktniDomenskiObjekat>();
			
			listaKoreografa.add(koreograf1);
			listaKoreografa.add(koreograf2);
			
			when(repository.vratiSve(any())).thenReturn(listaKoreografa);
			operacija.execute(koreograf1);
			verify(repository, times(1)).vratiSve(any());
			assertEquals(listaKoreografa, operacija.getListaKoreografa());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
