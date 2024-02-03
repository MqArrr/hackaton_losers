package syberry.api.banking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CurrencyNationalBank extends Currency{
    @JsonProperty("Cur_ID")
    private int curId;

    @JsonProperty("Cur_Code")
    private String curCode;

    @JsonProperty("Cur_Name_Eng")
    private String curNameEng;

    @JsonProperty("Cur_DateStart")
    private Date curDateStart;

    @JsonProperty("Cur_DateEnd")
    private Date curDateEnd;
}
