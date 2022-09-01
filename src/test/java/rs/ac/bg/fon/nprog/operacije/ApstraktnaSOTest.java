package rs.ac.bg.fon.nprog.operacije;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.nprog.domen.Koreograf;
import rs.ac.bg.fon.nprog.repository.Repository;
import rs.ac.bg.fon.nprog.repository.db.DBRepository;
import rs.ac.bg.fon.nprog.repository.db.impl.RepositoryDBGeneric;

@RunWith(MockitoJUnitRunner.class)
public abstract class ApstraktnaSOTest {
	
	private AutoCloseable closeable;
	
	@Mock
	protected DBRepository repository;
	
	
	@BeforeEach public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach public void releaseMocks() throws Exception {
        closeable.close();
    }
	
	@Test
	void testApstraktnaSO() {
		Repository r=new RepositoryDBGeneric();
		assertNotNull(r);
	}

		
}
