package syberry.api.banking.entity;

public enum BankName {
    ALFA ("alfa-bank"),
    NATIONAL("national-bank"),
    BELARUS("balarus-bank");

    BankName(String name) {
        System.out.println(name);
    }
}
