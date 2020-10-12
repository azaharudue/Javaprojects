package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.domain.User;
import de.unidue.inf.is.utils.DBUtil;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public final class UserStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public UserStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void addUser(String userName, String realName) throws StoreException {
        try {
            String sql = "INSERT INTO DBP66.BENUTZER (BENUTZERNAME, NAME, EINTRITTSDATUM) VALUES ('" +
                    userName +
                    "', '" +
                    realName +
                    "', '2019-01-14 16:30:09.635920')";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            int i = preparedStatement.executeUpdate();
            int b = 0;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public User selectUserByUserName(String userName) throws StoreException {
        try {
            String sql = "select * from dbp66.Benutzer where benutzername = '" + userName + "'";
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
            if (rowData.isEmpty()) {
                return null;
            }
            User user = new User();
            user.setBenutzername(rowData.get("BENUTZERNAME").toString());
            user.setEintrittsdatum((Timestamp) (rowData.get("EINTRITTSDATUM")));
            user.setName(rowData.get("NAME").toString());

            return user;
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
