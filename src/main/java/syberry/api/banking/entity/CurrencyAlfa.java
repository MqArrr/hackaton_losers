package syberry.api.banking.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.List;

@Data
@JsonRootName(value = "rates")
public class CurrencyAlfa extends Currency {
    @JsonProperty("rates")
    List<String> rates;
}
