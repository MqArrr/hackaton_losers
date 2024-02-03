package syberry.api.banking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyDTO {
    Integer id;
    String name;
    BigDecimal value;
    char symbol;
}
