package Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToJSON {
    public static String convert(ResultSet set) throws SQLException, JSONException {
        JSONArray array = new JSONArray();

        ResultSetMetaData metaData = set.getMetaData();
        int columnNum = metaData.getColumnCount();

        while(set.next()){
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnNum; i++) {
                String key = metaData.getColumnLabel(i+1);
                String value = set.getString(key);
                jsonObject.put(key, value);
            }
            array.put(jsonObject);
        }
        return array.toString();
    }
}
