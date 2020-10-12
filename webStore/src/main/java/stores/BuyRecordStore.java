package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public final class BuyRecordStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public BuyRecordStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void addBuyRecord(String userName, String advId) throws StoreException {
        try {
            String sql = "INSERT INTO DBP66.KAUFT (BENUTZERNAME, ANZEIGEID) VALUES ('" +
                    userName +
                    "', " +
                    advId +
                    ")";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
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