package rs.ac.bg.fon.nprog.operacije.koreograf;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.operacije.ApstraktnaSOTest;

class VratiKoreografaSOTest extends ApstraktnaSOTest{
	
	VratiKoreografaSO operacija;
	
	@BeforeEach
	void setUp() throws Exception {
		operacija=new VratiKoreografaSO(repository);
	}

	@AfterEach
	void tearDown() throws Exception {
		operacija=null;
	}


	@Test
	void testExecuteOperation() {
		try {
			Koreograf koreograf = new Koreograf(1l, "Dragica","Ljubisavljevic",new Date(1, 2, 2000),"@","+38165822320","Klasican balet" );

			when(repository.vratiPoId(koreograf)).thenReturn(koreograf);
			operacija.execute(koreograf);
			verify(repository, times(1)).vratiPoId(koreograf);
			assertEquals(koreograf, operacija.getKoreograf());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
