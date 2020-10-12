package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.domain.Adv;
import de.unidue.inf.is.utils.ClobToStringUtils;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.ResultSetToListUtils;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class AdvStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public AdvStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public Adv selectAdvById(String id) throws StoreException {
        try {
            String sql = "select * from dbp66.ANZEIGE where id =  '" + id + "'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            Map rowData = new HashMap();
            while (resultSet.next()) {
                rowData = new HashMap(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                break;
            }
            Adv adv = new Adv();
            adv.setId(rowData.get("ID").toString());
            adv.setTitel(rowData.get("TITEL").toString());
            try {
                adv.setText(ClobToStringUtils.ClobToString((Clob) (rowData.get("TEXT"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
            adv.setPreis(new BigDecimal(rowData.get("PREIS").toString()));
            adv.setErstellungsdatum((Timestamp) (rowData.get("ERSTELLUNGSDATUM")));
            adv.setErsteller(rowData.get("ERSTELLER").toString());
            adv.setStatus(rowData.get("STATUS").toString());

            return adv;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public int selectAdvCountOfUser(String userName) throws StoreException {
        try {
            String sql = "select count(1) as num from dbp66.ANZEIGE where ersteller =  '" + userName + "'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            Map rowData = new HashMap();
            while (resultSet.next()) {
                rowData = new HashMap(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                break;
            }

            return Integer.parseInt(rowData.get("NUM").toString());
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Adv> selectAllAdv() throws StoreException, IOException {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from dbp66.ANZEIGE where status = 'aktiv'");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Adv> selectSoldAdvByUser(String userName) throws StoreException, IOException {
        try {
            String sql = "select * from dbp66.ANZEIGE where ersteller =  '" + userName + "' and status = 'verkauft'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Adv> selectPurcheAdvByUser(String userName) throws StoreException, IOException {
        try {
            String sql = " select cmd.id as id, cmd.titel as titel, cmd.text as text, cmd.preis as preis, cmd.erstellungsdatum as erstellungsdatum, cmd.ersteller as ersteller" +
                    "    from dbp66.Kauft reco inner join dbp66.Anzeige cmd on cmd.id = reco.anzeigeID where reco.benutzername = '" +
                    userName +
                    "'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public List<Adv> getSortedAdvList(String sortBy) throws StoreException, IOException {
        String sql = null;
        try {

            if (sortBy.equals("Titel")) {
                sql = "select * from dbp66.ANZEIGE where status = 'aktiv' order by titel";
            } else {
                sql = "select * from dbp66.ANZEIGE where status = 'aktiv' order by erstellungsdatum desc";
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Adv> getAdvListWithFilter(String categorie) throws StoreException, IOException {
        String sql = null;
        try {
            sql = "select adv.id as id,  adv.titel as titel,  adv.text as text,  adv.preis as preis, " +
                    "adv.erstellungsdatum as erstellungsdatum, adv.ersteller as ersteller " +
                    "from dbp66.Anzeige adv inner join dbp66.HatKategorie cate " +
                    "on adv.id = cate.anzeigeID " +
                    "where cate.kategorie = '" +
                    categorie + "'";

            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public void buyAdv(String advId) throws StoreException {
        try {
            String sql = "update DBP66.ANZEIGE set status = 'verkauft' where id = " + advId;
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public void deleteAdv(String advId) throws StoreException {
        try {
            String sql = "delete from DBP66.ANZEIGE where id = " + advId;
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public void addAdv(String titel, String kategorie, String price, String description, String currentUser) throws StoreException {
        try {
            String sql = "INSERT INTO DBP66.ANZEIGE (TITEL, TEXT, PREIS, ERSTELLUNGSDATUM, ERSTELLER, STATUS)" +
                    "VALUES ('" +
                    titel +
                    "', '" +
                    description +
                    "', " +
                    price +
                    ", '2019-01-18 15:03:55.146448', '" +
                    currentUser +
                    "', 'aktiv')";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public void updateAdv(String advId, String titel, String kategorie, String price, String description, String currentUser) throws StoreException {
        try {
            String sql = "update DBP66.ANZEIGE set TITEL='" +
                    titel +
                    "', TEXT='" +
                    description +
                    "', PREIS='" +
                    price +
                    "', ERSTELLUNGSDATUM = '2019-01-18 15:06:55.146448',ERSTELLER='" +
                    currentUser +
                    "', STATUS='aktiv' where id = " + advId;
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public List<Adv> selectOffesAdvByUser(String userName) throws StoreException, IOException {
        try {
            String sql = "select * from dbp66.ANZEIGE where ersteller =  '" + userName + "'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Adv> advList = ResultSetToListUtils.resultSetToList(resultSet);
            return advList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void complete() {
        complete = true;
    }


    @Override
    public void close() throws IOException {
        if (connection != null) {
            try {
                if (complete) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } catch (SQLException e) {
                throw new StoreException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new StoreException(e);
                }
            }
        }
    }

}
