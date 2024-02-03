package syberry.api.banking.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syberry.api.banking.controller.exception.BankNotFoundException;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Data
@Service
public class BankServiceFactory {
    private final Map<String, BankService> map;

    public BankService createByBankName(String bankName) {
        var bankService = map.get(bankName.toUpperCase());
        if (Objects.isNull(bankService)){
            throw new BankNotFoundException();
        }
        return bankService;
    }

}
