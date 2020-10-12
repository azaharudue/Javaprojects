package Utils;

import java.io.IOException;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class ResultSetToListUtils {
    public static List resultSetToList(ResultSet rs) throws java.sql.SQLException, IOException {
        if (rs == null)
            return Collections.EMPTY_LIST;
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List list = new ArrayList();
        Map rowData = new HashMap();
        while (rs.next()) {
            rowData = new HashMap(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String className = md.getColumnClassName(i);
                if ("java.sql.Clob".equals(className)) {
                    String clobToStr = ClobToStringUtils.ClobToString((Clob) rs.getObject(i));
                    rowData.put(md.getColumnName(i), clobToStr);
                    continue;
                }
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
            System.out.println("list:" + list.toString());
        }
        return list;
    }
}
