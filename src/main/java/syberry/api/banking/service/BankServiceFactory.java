package syberry.api.banking.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Data
@Service
public class BankServiceFactory {
    private final Map<String, BankService> map;

    public BankService createByBankName(String bankName) {
        return map.get(bankName.toUpperCase());
    }
}
