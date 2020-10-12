package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.domain.Chat;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.ResultSetToListUtils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class ChatStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public ChatStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public void addChat(String currentUser, String chatWith, String chatData) throws StoreException {
        try {
            String sql = "INSERT INTO DBP66.NACHRICHT ( TEXT, ABSENDER, EMPFAENGER) VALUES ('" +
                    chatData +
                    "', '" +
                    currentUser +
                    "', '" + chatWith +
                    "')";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }


    public List<Chat> getHisChat(String currentUser, String chatWith) throws StoreException, IOException {
        try {
            String sql = "select * from dbp66.Nachricht where (absender = '" +
                    currentUser +
                    "' and empfaenger= '" +
                    chatWith +
                    "') or  (absender = '" +
                    chatWith +
                    "' and empfaenger= '" +
                    currentUser +
                    "') order by id ";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Chat> chatList = ResultSetToListUtils.resultSetToList(resultSet);
            return chatList;
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