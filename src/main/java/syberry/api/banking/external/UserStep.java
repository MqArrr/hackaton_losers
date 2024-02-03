package syberry.api.banking.external;

import lombok.Data;

@Data
public class UserStep {
    private int bank = NATIONAL;
    private int curr = USD;

    public static final int NATIONAL = 0, BELARUS = 1, ALPHA = 2;
    public static final int USD = 0;
    public static final int EUR = 1;
    public static final int JPY = 2;
    public static final int GBP = 3;



}
