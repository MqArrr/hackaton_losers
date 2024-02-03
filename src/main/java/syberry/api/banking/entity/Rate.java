package syberry.api.banking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rate {
    @JsonProperty("Cur_ID")
    private String id;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Cur_Abbreviation")
    private String abbreviation;
    @JsonProperty("Cur_Scale")
    private String scale;
    @JsonProperty("Cur_Name")
    private String name;
    @JsonProperty("Cur_OfficialRate")
    private String officialRate;
}

