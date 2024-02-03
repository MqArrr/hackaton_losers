package syberry.api.banking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CurrencyNationalBank extends Currency{
    @JsonProperty("Cur_ID")
    private int curId;

    @JsonProperty("Cur_ParentID")
    private int curParentId;

    @JsonProperty("Cur_Code")
    private String curCode;

    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

    @JsonProperty("Cur_Name")
    private String curName;

    @JsonProperty("Cur_Name_Bel")
    private String curNameBel;

    @JsonProperty("Cur_Name_Eng")
    private String curNameEng;

    @JsonProperty("Cur_QuotName")
    private String curQuotName;

    @JsonProperty("Cur_QuotName_Bel")
    private String curQuotNameBel;

    @JsonProperty("Cur_QuotName_Eng")
    private String curQuotNameEng;

    @JsonProperty("Cur_NameMulti")
    private String curNameMulti;

    @JsonProperty("Cur_Name_BelMulti")
    private String curNameBelMulti;

    @JsonProperty("Cur_Name_EngMulti")
    private String curNameEngMulti;

    @JsonProperty("Cur_Scale")
    private int curScale;

    @JsonProperty("Cur_Periodicity")
    private int curPeriodicity;

    @JsonProperty("Cur_DateStart")
    private Date curDateStart;

    @JsonProperty("Cur_DateEnd")
    private Date curDateEnd;
}
