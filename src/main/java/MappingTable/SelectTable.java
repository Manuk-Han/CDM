package MappingTable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SelectTable {
    USER_TO_PERSON("USER_TB", "user_sn, user_gender, user_birth"),
    OTOSCOPE_TO_OBSERVATION_PERIOD("OTOSCOPE_TB", "user_fk, otoscope_datetime"),
    STETHOSCOPE_TO_OBSERVATION_PERIOD("STETHOSCOPE_TB", "user_fk, stethoscope_datetime"),
    THERMOMETER_TO_OBSERVATION_PERIOD("THERMOMETER_TB", "user_fk, thermometer_datetime"),
    ASTHMA_MEDICINE_TO_OBSERVATION_PERIOD("ASTHMA_MEDICINE_TB", "asthma_onset_fk"),
    INSPIRATOR_TO_OBSERVATION_PERIOD("INSPIRATOR_TB", "user_fk, inspirator_datetime"),

    USER_TO_VISIT_OCCURRENCE("USER_TB", "user_sn, user_lastday"),

    THERMOMETER_TO_CONDITION_OCCURRENCE("THERMOMETER_TB", "user_fk, thermometer_datetime"),
    ASTHMA_ONSET_TO_CONDITION_OCCURRENCE("ASTHMA_ONSET_TB", "user_fk, asthma_onset_date"),
    OTOSCOPE_TO_CONDITION_OCCURRENCE("OTOSCOPE_TB", "user_fk, otoscope_datetime"),
    INSPIRATOR_TO_CONDITION_OCCURRENCE("INSPIRATOR_TB", "user_fk, inspirator_datetime"),

    // 미정
    DEVICE_TO_DEVICE_EXPOSURE("DEVICE_TB", "device_collection_date, device_model_device_app_ver"),
    DEVICE_USER_MAP_TO_DEVICE_EXPOSURE("DEVICE_USER_MAP", "user_fk, device_fk"),

    THERMOMETER_TO_MEASUREMENT("THERMOMETER_TB", "user_fk, thermometer_datetime"),
    STETHOSCOPE_TO_MEASUREMENT("STETHOSCOPE_TB", "user_fk, stethoscope_datetime"),
    OTOSCOPE_TO_MEASUREMENT("OTOSCOPE_TB", "user_fk, otoscope_datetime"),
    INSPIRATOR_TO_MEASUREMENT("INSPIRATOR_TB", "user_fk, inspirator_datetime");

    private final String tableName;

    private final String needColumns;
}
