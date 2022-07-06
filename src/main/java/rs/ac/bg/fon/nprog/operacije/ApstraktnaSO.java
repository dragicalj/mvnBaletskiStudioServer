package rs.ac.bg.fon.nprog.operacije;

import rs.ac.bg.fon.nprog.repository.Repository;
import rs.ac.bg.fon.nprog.repository.db.DBRepository;
import rs.ac.bg.fon.nprog.repository.db.impl.RepositoryDBGeneric;

public abstract class ApstraktnaSO {
	
	protected final Repository repository;
	   
    public ApstraktnaSO() {
        this.repository = new RepositoryDBGeneric();
    }

    
    public void execute(Object param) throws Exception {
        try {
            precondition(param);
            startTransaction();
            executeOperation(param);
            comitTransaction();
            System.out.println("Uspesno izvrsena operacija!!!");
        } catch (Exception exception) {
            System.out.println("Greska u izvrsavanju operacije!!!");
            rollbackTransaction();
            throw exception;
        } finally{
            disconnect();
        }
    }

    protected abstract void precondition(Object param)throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DBRepository) repository).uspostaviKonekciju();
    }

    protected void comitTransaction() throws Exception{
         ((DBRepository) repository).potvrdiTransakciju();
    }

    protected void rollbackTransaction() throws Exception {
        ((DBRepository) repository).ponistiTransakciju();
    }
    
    private void disconnect() throws Exception {
        ((DBRepository) repository).raskiniKonekciju();
    }
}
