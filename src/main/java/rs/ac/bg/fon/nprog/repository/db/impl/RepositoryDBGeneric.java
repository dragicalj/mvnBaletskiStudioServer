package rs.ac.bg.fon.nprog.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import rs.ac.bg.fon.nprog.domen.ApstraktniDomenskiObjekat;
import rs.ac.bg.fon.nprog.repository.db.DBKonekcije;
import rs.ac.bg.fon.nprog.repository.db.DBRepository;

public class RepositoryDBGeneric implements DBRepository{
	@Override
    public ApstraktniDomenskiObjekat vratiPoId(ApstraktniDomenskiObjekat ado) throws Exception{
        List<ApstraktniDomenskiObjekat> lista = null;
        try {
            Connection connection = DBKonekcije.getInstance().getConnection();
            String upit = "SELECT * FROM " + ado.vratiNazivTabele()+ " WHERE " + ado.vratiUslovZaSelect();
            System.out.println(upit);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(upit);

            lista = ado.vratiListu(resultSet);

            statement.close();
            return lista.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Ne postoji!");
        }

    }

    @Override
    public Long kreirajSlog(ApstraktniDomenskiObjekat ado) throws Exception{
       try {
            String upit;
            Connection connection = DBKonekcije.getInstance().getConnection();
            upit = "INSERT INTO " + ado.vratiNazivTabele()+ "(" + ado.vratiNaziveKolonaZaInsert()+ ")" + " VALUES(" + ado.vratiVrednostiZaInsert() + ")";
            System.out.println(upit);
            PreparedStatement preparedStatement = connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs != null && rs.next()) {
                return rs.getLong(1);
            } else {
                return 0L;
            }
        } catch (Exception ex) {
            throw ex;
        } 
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiSve(ApstraktniDomenskiObjekat apstraktniDomenskiObjekat) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = null;
        
        try {
            Connection connection = DBKonekcije.getInstance().getConnection();
            String query = "SELECT * FROM " + apstraktniDomenskiObjekat.vratiNazivTabele();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = apstraktniDomenskiObjekat.vratiListu(resultSet);
            //System.out.println(lista+"dbbbbbbbbbbbbbbbb");
            resultSet.close();
            statement.close();
            return lista;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void promeni(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            String query;
            Connection connection = DBKonekcije.getInstance().getConnection();
            query = "UPDATE " + ado.vratiNazivTabele()+ " SET " + ado.postaviVrednostiAtributa()+ " WHERE " + ado.vratiUslovZaPromenuVrednostiAtributa();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.commit();
            statement.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<ApstraktniDomenskiObjekat> pronadji(ApstraktniDomenskiObjekat ado) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = null;
        
        try {
            Connection connection = DBKonekcije.getInstance().getConnection();
            String query = "SELECT * FROM " + ado.vratiNazivTabele()+ " WHERE " + ado.vratiUslovZaPretragu();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = ado.vratiListu(resultSet);
            resultSet.close();
            statement.close();
            return lista;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiPoUslovu(ApstraktniDomenskiObjekat ado) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = null;
        try {
            Connection connection = DBKonekcije.getInstance().getConnection();
            String query = "SELECT * FROM " + ado.vratiNazivTabele()+ " WHERE " + ado.vratiUslovZaPretragu();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = ado.vratiListu(resultSet);
            //System.out.println(lista);
            resultSet.close();
            statement.close();
            return lista;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public boolean daLiPostoji(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            String upit = "SELECT * FROM " + ado.vratiNazivTabele()+ " WHERE " + ado.vratiUslovZaSelect();
            Connection connection = DBKonekcije.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<ApstraktniDomenskiObjekat> pronadji2(ApstraktniDomenskiObjekat ado) throws Exception {
         List<ApstraktniDomenskiObjekat> lista = null;
        
        try {
            Connection connection = DBKonekcije.getInstance().getConnection();
            String query = "SELECT * FROM " + ado.vratiNazivTabele()+ " WHERE " + ado.vratiUslovZaPretragu2();
            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            lista = ado.vratiListu(resultSet);
            resultSet.close();
            statement.close();
            return lista;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void obrisi(ApstraktniDomenskiObjekat ado) throws Exception {
        try {
            String query;
            Connection connection = DBKonekcije.getInstance().getConnection();
            query = "DELETE FROM " + ado.vratiNazivTabele() + " WHERE " + ado.vratiUslovZaSelect();
            System.out.println(query);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
