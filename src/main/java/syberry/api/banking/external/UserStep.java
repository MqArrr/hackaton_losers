package syberry.api.banking.external;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserStep {
    private int bank = NOT_SET;
    private int curr = NOT_SET;

    public static final int NATIONAL = 0, BELARUS = 1, ALPHA = 2;
    public static final int USD = 0;
    public static final int EUR = 1;
    public static final int JPY = 2;
    public static final int GBP = 3;
    public static final int NOT_SET = -1;


    public static final Map<Integer, String> bankMap = new HashMap<>();
    public static final Map<Integer, String> currencyMap = new HashMap<>();
    {
        bankMap.put(0, "Нацбанк");
        bankMap.put(1, "Беларусбанк");
        bankMap.put(2, "Альфа банк");
        currencyMap.put(0, "USD");
        currencyMap.put(1, "EUR");
        currencyMap.put(2, "JPY");
        currencyMap.put(3, "GBP");

    }

}
