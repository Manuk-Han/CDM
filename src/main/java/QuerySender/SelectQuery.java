package QuerySender;

import MappingTable.SelectFkTable;
import MappingTable.SelectTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectQuery {
    private Connection originCon;

//    private final String originDriverClass = "org.mariadb.jdbc.Driver";
//    private final String originUsername = "multi";
//    private final String originPassword = "0120";
//    private final String originUrl = "jdbc:mariadb://intindev.com:3309/multi";

    private String originDriverClass = "net.sf.log4jdbc.sql_1.jdbcapi.DriverSpy";
    private String originUsername = "multi";
    private String originPassword = "0120";
    private String originUrl = "jdbc:log4jdbc:mariadb://intindev.com:3309/multi";

    public String select(SelectTable table) throws SQLException {

        try {
            Class.forName(originDriverClass).newInstance();
            originCon = DriverManager.getConnection(originUrl, originUsername, originPassword);
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

        String sql = "select " + table.getNeedColumns() + " from " + table.getTableName() + ";";
        PreparedStatement stmt = null;

        String resultSQL = null;

        try {
            stmt = originCon.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            resultSQL = makeSQL(rs, table);
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
        originCon.close();

        return resultSQL;
    }

    public List<String> selectFk(SelectFkTable table, int fk) throws SQLException {

        try {
            Class.forName(originDriverClass).newInstance();
            originCon = DriverManager.getConnection(originUrl, originUsername, originPassword);
        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            System.out.println("Exception" + e);
        }

        String sql = "select " + table.getNeedColumns() + " from " + table.getTableName() + " where " + table.getWhere() + fk + ";";
        PreparedStatement stmt = null;

        List<String> resultSQL = new ArrayList<>();

        try {
            stmt = originCon.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String resultSet = "";
                for(int i = 0 ; i < table.getNeedColumns().split(", ").length ; i++)
                    resultSet += rs.getString(table.getNeedColumns().split(", ")[i]) + ", ";
                resultSQL.add(resultSet.substring(0, resultSet.length()-2));
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
        originCon.close();

        return resultSQL;
    }

    private String makeSQL(ResultSet rs, SelectTable table) throws SQLException {
        StringBuilder stringBuilder = new StringBuilder();

        switch (table){
            case USER_TO_PERSON:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    int genderId = new SelectConceptQuery().selectGenderConcept(rs.getString(table.getNeedColumns().split(", ")[1]));
                    int year = rs.getInt(table.getNeedColumns().split(", ")[2]) / 10000;
                    int month = rs.getInt(table.getNeedColumns().split(", ")[2]) % 10000 / 100;
                    int day = rs.getInt(table.getNeedColumns().split(", ")[2]) % 100;

                    stringBuilder.append("(" +
                            personId + ", " +
                            genderId + ", " +
                            year + ", " +
                            month + ", " +
                            day + ", " +
                            "0, 38003564), ");
                }
                break;
            case OTOSCOPE_TO_OBSERVATION_PERIOD:
            case STETHOSCOPE_TO_OBSERVATION_PERIOD:
            case THERMOMETER_TO_OBSERVATION_PERIOD:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date observationPeriodStartDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "\'" + observationPeriodStartDate + "\', " +
                            "\'" + observationPeriodStartDate + "\', " +
                            "705183), ");
                }
                break;
            case ASTHMA_MEDICINE_TO_OBSERVATION_PERIOD:
                while (rs.next()) {
                    int asthmaMedicineId = rs.getInt(table.getNeedColumns());

                    List<String> dataList = selectFk(SelectFkTable.ASTHMA_ONSET_TB, asthmaMedicineId);

                    for(String data : dataList) {
                        int personId = Integer.parseInt(data.split(", ")[0]);
                        Date observationPeriodStartDate = Date.valueOf(data.split(", ")[1]);

                        stringBuilder.append("(" +
                                personId + ", " +
                                "\'" + observationPeriodStartDate + "\', " +
                                "\'" + observationPeriodStartDate + "\', " +
                                "507636), ");
                    }
                }
                break;
            case USER_TO_VISIT_OCCURRENCE:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date visitStartDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "5083, " +
                            "\'" + visitStartDate + "\', " +
                            "\'" + visitStartDate + "\', " +
                            "3564527), ");
                }
                break;
            case THERMOMETER_TO_CONDITION_OCCURRENCE:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "4118266, " +
                            "\'" + userLastDate + "\', " +
                            "45905770), ");
                }
                break;
            case ASTHMA_ONSET_TO_CONDITION_OCCURRENCE:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "317009, " +
                            "\'" + userLastDate + "\', " +
                            "3521979), ");
                }
                break;
            case OTOSCOPE_TO_CONDITION_OCCURRENCE:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "4013007, " +
                            "\'" + userLastDate + "\', " +
                            "45905770), ");
                }
                break;
            case INSPIRATOR_TO_CONDITION_OCCURRENCE:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "11111, " +
                            "\'" + userLastDate + "\', " +
                            "45905770), ");
                }
                break;
            case DEVICE_TO_DEVICE_EXPOSURE:
            case DEVICE_USER_MAP_TO_DEVICE_EXPOSURE:
                break;
            case OTOSCOPE_TO_MEASUREMENT:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "37398080, " +
                            "\'" + userLastDate + "\', " +
                            "36674460), ");
                }
                break;
            case STETHOSCOPE_TO_MEASUREMENT:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "1002748, " +
                            "\'" + userLastDate + "\', " +
                            "36676650), ");
                }
                break;
            case THERMOMETER_TO_MEASUREMENT:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "3020891, " +
                            "\'" + userLastDate + "\', " +
                            "44802373), ");
                }
                break;
            case INSPIRATOR_TO_MEASUREMENT:
                while (rs.next()) {
                    int personId = rs.getInt(table.getNeedColumns().split(", ")[0]);
                    Date userLastDate = rs.getDate(table.getNeedColumns().split(", ")[1]);

                    stringBuilder.append("(" +
                            personId + ", " +
                            "4033701, " +
                            "\'" + userLastDate + "\', " +
                            "3564916), ");
                }
                break;
        }

        return stringBuilder.toString().substring(0, stringBuilder.length() - 2);
    }
}
