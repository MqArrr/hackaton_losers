package syberry.api.banking.entity;

public enum EBankName {
    ALFABANK ("ALFABANK"),
    NATIONALBANK("NATIONALBANK"),
    BELARUSBANK("BELARUSBANK");

    EBankName(String name) {
        System.out.println(name);
    }
}
