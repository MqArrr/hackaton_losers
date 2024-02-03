package syberry.api.banking.entity;

public enum EBankName {
    ALFA ("ALFABANK"),
    NATIONAL("NATIONALBANK"),
    BELARUS("BELARUSBANK");

    EBankName(String name) {
        System.out.println(name);
    }
}
