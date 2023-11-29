import MappingTable.InsertTable;
import MappingTable.SelectTable;
import QuerySender.InsertQuery;
import QuerySender.SelectQuery;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SelectQuery selectQuery = new SelectQuery();
        InsertQuery insertQuery = new InsertQuery();

        insertQuery.insert(InsertTable.PERSON, selectQuery.select(SelectTable.USER_TO_PERSON));

        insertQuery.insert(InsertTable.OBSERVATION_PERIOD, selectQuery.select(SelectTable.OTOSCOPE_TO_OBSERVATION_PERIOD));
        insertQuery.insert(InsertTable.OBSERVATION_PERIOD, selectQuery.select(SelectTable.STETHOSCOPE_TO_OBSERVATION_PERIOD));
        insertQuery.insert(InsertTable.OBSERVATION_PERIOD, selectQuery.select(SelectTable.THERMOMETER_TO_OBSERVATION_PERIOD));
        insertQuery.insert(InsertTable.OBSERVATION_PERIOD, selectQuery.select(SelectTable.ASTHMA_MEDICINE_TO_OBSERVATION_PERIOD));

        insertQuery.insert(InsertTable.VISIT_OCCURRENCE, selectQuery.select(SelectTable.USER_TO_VISIT_OCCURRENCE));

        insertQuery.insert(InsertTable.CONDITION_OCCURRENCE, selectQuery.select(SelectTable.THERMOMETER_TO_CONDITION_OCCURRENCE));
        insertQuery.insert(InsertTable.CONDITION_OCCURRENCE, selectQuery.select(SelectTable.ASTHMA_ONSET_TO_CONDITION_OCCURRENCE));
        insertQuery.insert(InsertTable.CONDITION_OCCURRENCE, selectQuery.select(SelectTable.OTOSCOPE_TO_CONDITION_OCCURRENCE));
        insertQuery.insert(InsertTable.CONDITION_OCCURRENCE, selectQuery.select(SelectTable.INSPIRATOR_TO_CONDITION_OCCURRENCE));

        insertQuery.insert(InsertTable.MEASUREMENT, selectQuery.select(SelectTable.THERMOMETER_TO_MEASUREMENT));
        insertQuery.insert(InsertTable.MEASUREMENT, selectQuery.select(SelectTable.STETHOSCOPE_TO_MEASUREMENT));
        insertQuery.insert(InsertTable.MEASUREMENT, selectQuery.select(SelectTable.OTOSCOPE_TO_MEASUREMENT));
        insertQuery.insert(InsertTable.MEASUREMENT, selectQuery.select(SelectTable.INSPIRATOR_TO_CONDITION_OCCURRENCE));

    }
}
