package QuerySender;

import java.sql.*;
import java.util.Objects;

public class SelectConceptQuery {
    private Connection cdmCon;

//    private String cdmDriverClass = "org.postgresql.Driver";
//    private String cdmUsername = "postgres";
//    private String cdmPassword = "postgres";
//    private String cdmUrl = "jdbc:postgresql://localhost:5432/postgres";

    private String cdmDriverClass = "net.sf.log4jdbc.sql_1.jdbcapi.DriverSpy";
    private String cdmUsername = "postgres";
    private String cdmPassword = "postgres";
    private String cdmUrl = "jdbc:log4jdbc:postgresql://localhost:5432/postgres";

    public SelectConceptQuery() {
        try {
            Class.forName(cdmDriverClass).newInstance();
            cdmCon = DriverManager.getConnection(cdmUrl, cdmUsername, cdmPassword);
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }
    }

    public int selectGenderConcept(String data) throws SQLException {
        if(Objects.equals(data, "Etc")) data = "other";

        String sql = "select concept_id from cdm.concept where concept_name = \'" + data.toUpperCase() + "\';";
        PreparedStatement stmt = null;

        int result = 0;
        try {
            stmt = cdmCon.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result = rs.getInt("concept_id");
            }
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

        return result;
    }
}
