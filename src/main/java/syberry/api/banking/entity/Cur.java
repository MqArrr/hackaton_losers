package syberry.api.banking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cur {
    @JsonProperty("sellRate")
    private String sellRate;
    @JsonProperty("sellIso")
    private String sellIso;
    @JsonProperty("sellCode")
    private String sellCode;
    @JsonProperty("buyRate")
    private String buyRate;
    @JsonProperty("buyIso")
    private String  buyIso;
    @JsonProperty("buyCode")
    private String buyCode;
    @JsonProperty("quantity")
    private String quantity;
    @JsonProperty("name")
    private String name ;
    @JsonProperty("date")
    private String date;
}
