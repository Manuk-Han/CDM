package MappingTable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InsertTable {
    PERSON("cdm.person", "person_id, gender_concept_id, year_of_birth, month_of_birth, day_of_birth, race_concept_id, ethnicity_concept_id"),
    OBSERVATION_PERIOD("cdm.observation_period", "person_id, observation_period_start_date, observation_period_end_date,period_type_concept_id"),
    VISIT_OCCURRENCE("cdm.visit_occurrence", "person_id, visit_concept_id, visit_start_date, visit_end_date , visit_type_concept_id"),
    CONDITION_OCCURRENCE("cdm.condition_occurrence", "person_id, condition_concept_id, condition_start_date, condition_type_concept_id"),
    DEVICE_EXPOSURE("cdm.device_exposure", "person_id, device_concept_id, device_exposure_start_date, device_type_concept_id"),
    MEASUREMENT("cdm.measurement", "person_id, measurement_concept_id, measurement_date, measurement_type_concept_id");

    private final String tableName;

    private final String needColumns;
}
