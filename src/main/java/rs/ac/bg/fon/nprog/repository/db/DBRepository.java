package rs.ac.bg.fon.nprog.repository.db;

public interface DBRepository {
	
	 	default public void uspostaviKonekciju() throws Exception{
	        DBKonekcije.getInstance().getConnection();
	    }
	    
	    default public void raskiniKonekciju() throws Exception{
	        DBKonekcije.getInstance().getConnection().close();
	    }
	    
	    default public void potvrdiTransakciju() throws Exception{
	        DBKonekcije.getInstance().getConnection().commit();
	    }
	    
	    default public void ponistiTransakciju() throws Exception{
	        DBKonekcije.getInstance().getConnection().rollback();
	    }
}
