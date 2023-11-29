package QuerySender;

import MappingTable.InsertTable;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor
public class InsertQuery {
    private Connection cdmCon;

//    private String cdmDriverClass = "org.postgresql.Driver";
//    private String cdmUsername = "postgres";
//    private String cdmPassword = "postgres";
//    private String cdmUrl = "jdbc:postgresql://localhost:5432/postgres";

    private String cdmDriverClass = "net.sf.log4jdbc.sql_1.jdbcapi.DriverSpy";
    private String cdmUsername = "postgres";
    private String cdmPassword = "postgres";
    private String cdmUrl = "jdbc:log4jdbc:postgresql://localhost:5432/postgres";

    public void insert(InsertTable table, String data) throws SQLException {

        try {
            Class.forName(cdmDriverClass).newInstance();
            cdmCon = DriverManager.getConnection(cdmUrl, cdmUsername, cdmPassword);
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

        String sql = "insert into " + table.getTableName() + "( " + table.getNeedColumns() + ") values " + data +";";

        Statement stmt = null;

        try {
            stmt = cdmCon.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null && !stmt.isClosed())
                    stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cdmCon.close();
    }

}
