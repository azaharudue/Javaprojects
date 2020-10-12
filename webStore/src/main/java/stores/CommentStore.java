package stores;

import de.unidue.inf.is.constant.DBConstant;
import de.unidue.inf.is.domain.Comment;
import de.unidue.inf.is.domain.Kommentar;
import de.unidue.inf.is.utils.DBUtil;
import de.unidue.inf.is.utils.ResultSetToListUtils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public final class CommentStore implements Closeable {

    private Connection connection;
    private boolean complete;


    public CommentStore() throws StoreException {
        try {
            connection = DBUtil.getExternalConnection(DBConstant.DB_NAME);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public List<Comment> selectCommentOnAdvByAdvId(String advId) throws StoreException, IOException {
        try {
            String sql = "select usr.benutzername as userName, cmd.text as text  from dbp66.HatKommentar cmm inner join dbp66.Kommentar cmd on  cmm.kommentarID = cmd.id " +
                    "inner join dbp66.Benutzer usr on cmm.benutzername =  usr.benutzername " +
                    "inner join dbp66.Anzeige adv on adv.id = cmm.anzeigeID where adv.id = '" + advId + "'";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Comment> commentList = ResultSetToListUtils.resultSetToList(resultSet);
            return commentList;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }

    public void deleteCommentByAdvId(String advId) {

        try {
            //delete Kommentar and HatKommentar will bu delete automaticallly
            String sql = "delete from dbp66.Kommentar where id in" +
                    "(select kommentarID from dbp66.HatKommentar hcmd inner join dbp66.Kommentar cmd on cmd.id = hcmd.kommentarID where hcmd.anzeigeID = '" +
                    advId +
                    "')";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }

    }

    public Kommentar selectLastComment() throws StoreException {
        try {
            String sql = "select * from dbp66.KOMMENTAR order by erstellungsdatum desc";
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
            Kommentar kommentar = new Kommentar();
            kommentar.setId(Integer.parseInt(rowData.get("ID").toString()));
            kommentar.setErstellungsdatum(Timestamp.valueOf(rowData.get("ERSTELLUNGSDATUM").toString()));
            kommentar.setText(rowData.get("TEXT").toString());

            return kommentar;
        } catch (SQLException e) {
            throw new StoreException(e);
        }
    }


    public void addComment(String text, String timeStamp) throws StoreException {
        try {
            Random random = new Random();
            int id = random.nextInt(30000) + 10;

            String sql = "INSERT INTO DBP66.KOMMENTAR ( TEXT, ERSTELLUNGSDATUM) VALUES ( '" +
                    text +
                    "','" +
                    timeStamp +
                    "')";
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sql);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new StoreException(e);
        }
    }

    public void addCommentBound(int commentId, String userName, int advId) throws StoreException {
        try {
            String sql = "INSERT INTO DBP66.HATKOMMENTAR (KOMMENTARID, BENUTZERNAME, ANZEIGEID) " +
                    "VALUES (" +
                    commentId +
                    ", '" +
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