package Utils;

import com.ibm.db2.jcc.DB2Driver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class DBUtil {

    static {
        com.ibm.db2.jcc.DB2Driver driver = new DB2Driver();
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new Error("Laden des Datenbanktreiber nicht möglich");
        }
    }


    private DBUtil() {
    }

    public static Connection getConnection(String database) throws SQLException {
        final String url = "jdbc:db2:" + database;
        return DriverManager.getConnection(url);
    }


    // Diese Methode benutzen, um sich von außerhalb der Uni mit der DB zu verbinden

    /**
     * @param database
     * @return
     * @throws SQLException
     */
    public static Connection getExternalConnection(String database) throws SQLException {
        Properties properties = new Properties();

        InputStream input = null;
        try {
            input = DBUtil.class.getClassLoader().getResourceAsStream("settings.properties");
            input = new FileInputStream("src/main/java/de/unidue/inf/is/utils/settings.properties");

//// Zugangsdaten aus der Properties-Datei lesen
//    		properties.load(input);
//    	} catch (IOException ex) {
//    		ex.printStackTrace();
//    	}
//		
            String user = "dbp66";
            String pass = "aing7iku";
            String rechnername =
                    "helios";


            String gruppennummer = user.substring(user.length() - 2, user.length());
            System.out.println(gruppennummer);

            final String url = "jdbc:db2://" + rechnername + ".is.inf.uni-due.de:500" + gruppennummer + "/" + database + ":currentSchema=" + user + ";";
            Connection connection = DriverManager.getConnection(url, user, pass);

            return connection;
        }


        public static boolean checkDatabaseExistsExternal (String database){
            // Nur für Demozwecke!
            boolean exists = false;

            try (Connection connection = getExternalConnection(database)) {
                exists = true;
            } catch (SQLException e) {
                exists = false;
                e.printStackTrace();
            }

            return exists;
        }


        public static boolean checkDatabaseExists (String database){
            // Nur für Demozwecke!
            boolean exists = false;

            try (Connection connection = getConnection(database)) {
                exists = true;
            } catch (SQLException e) {
                exists = false;
                e.printStackTrace();
            }

            return exists;
        }

    }
