package rs.ac.bg.fon.nprog.operacije;

import rs.ac.bg.fon.nprog.repository.Repository;
import rs.ac.bg.fon.nprog.repository.db.DBRepository;
import rs.ac.bg.fon.nprog.repository.db.impl.RepositoryDBGeneric;

/**
 * 
 * Klasa koja predstavlja apstraktnu sistemsku operaciju. 
 * Sadrzi sablon za izvrsavanje sistemske operacije. Neke operacije samostalno implementira,
 * dok neke specificne prepusta podklasama. 
 * Sve sistemske operacije nasledjuju ovu apstraktnu klasu.
 * 
 * @author Dragica Ljubisavljevic
 * @version 1.0
 *
 */
public abstract class ApstraktnaSO {
	
	/**
	 * Repozitorijum koji nam sluzi za sve operacije nad bazom podataka
	 */
	protected final Repository repository;
	 /**
	  * Neparametrizovani konstruktor u kome se vrsi inicijalizacija repozitorijuma
	  */
    public ApstraktnaSO() {
        this.repository = new RepositoryDBGeneric();
    }
    /**
     * Parametarski konstruktor, postavlja se vrednost repozitorijuma na prosledjenu
     * @param repository
     */
    public ApstraktnaSO(Repository repository) {
        this.repository = repository;
    }
    /**
     * Sablon za izvrsavanje sistemske operacije.
     * 
     * @param param parametar sa kojim ce sistemska operacija raditi tipa Object.
     * @throws Exception ukoliko dodje do greske prilikom izvrsavanja sistemske operacije.
     */
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
    /**
     * Metoda u kojoj se proveravaju preduslovi neophodni za sprovodjenje sistemske operacije
     * 
     * @param param parametar sa kojim ce sistemska operacija raditi tipa Object.
     * @throws Exception ukoliko neki od preduslova nije ispunjen.
     */
    protected abstract void precondition(Object param)throws Exception;
    /**
     * Metoda u kojoj se izvrsava sistemska operacija. Pozivaju se upiti nad bazom podataka.
     * 
     * @param param parametar sa kojim ce sistemska operacija raditi tipa Object.
     * @throws Exception ukoliko dodje do greske u izvrsavanju operacije.
     */
    protected abstract void executeOperation(Object param) throws Exception;
    /**
     * Metoda koja uspostavlja konekciju sa bazom podataka i oznacava pocetak transakcije.
     * 
     * @throws Exception ukoliko dodje do greske prilikom uspotavljanja konekcije.
     */
    private void startTransaction() throws Exception {
        ((DBRepository) repository).uspostaviKonekciju();
    }
    /**
     * Metoda u kojoj se potvrdjuje uspesna transakcija.
     * 
     * @throws Exception ukoliko dodje do greske prilikom commit-a.
     */
    protected void comitTransaction() throws Exception{
         ((DBRepository) repository).potvrdiTransakciju();
    }
    /**
     * Metoda u kojoj se ponistava transakcija.
     * 
     * @throws Exception ukoliko dodje do greske prilikom rollback-a.
     */
    protected void rollbackTransaction() throws Exception {
        ((DBRepository) repository).ponistiTransakciju();
    }
    /**
     * Metoda koja raskida konekciju sa bazom podataka i oznacava kraj transakcije.
     * 
     * @throws Exception ukoliko dodje do greske prilikom raskida konekcije,
     */
    private void disconnect() throws Exception {
        ((DBRepository) repository).raskiniKonekciju();
    }
}
