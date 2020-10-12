package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategorieStore implements Closeable {
    private Connection connection;
    private boolean complete;


    public CategorieStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public void addCateOfAdv(String titel, String kategorie, String price, String description, String currentUser) throws StoreException {
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
