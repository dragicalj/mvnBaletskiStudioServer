package rs.ac.bg.fon.nprog.repository.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import rs.ac.bg.fon.nprog.konfiguracija.PodaciZaKonfiguraciju;

public class DBKonekcije {
	
	private Connection connection;
    private static DBKonekcije instance;

    private DBKonekcije() {
    }
    
    public static DBKonekcije getInstance(){
        if(instance == null){
            instance = new DBKonekcije();
        }
        
        return instance;
    }
    
    public Connection getConnection() throws SQLException, FileNotFoundException, IOException{
        if (connection == null || connection.isClosed()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(PodaciZaKonfiguraciju.PATH));
                String url = properties.getProperty(PodaciZaKonfiguraciju.URL);
                String user = properties.getProperty(PodaciZaKonfiguraciju.USERNAME);
                String password = properties.getProperty(PodaciZaKonfiguraciju.PASSWORD);
                
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!" + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
        instance = null;
    }
}
