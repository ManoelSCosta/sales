import java.sql.SQLException;
import java.sql.Types;

public class Util {
    public static String properCase(String s) {
        try (var conn = DB.connect();
             var stmt = conn.prepareCall("{ ? = call initcap(?) }")) {
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, s);
            stmt.execute();
            return stmt.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
