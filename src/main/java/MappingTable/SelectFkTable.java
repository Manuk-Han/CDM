package MappingTable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SelectFkTable {
    ASTHMA_ONSET_TB("ASTHMA_ONSET_TB", "user_fk, asthma_onset_date", "asthma_onset_sn = ");

    private final String tableName;

    private final String needColumns;

    private final String where;
}