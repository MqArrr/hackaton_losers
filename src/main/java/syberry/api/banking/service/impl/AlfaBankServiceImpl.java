package syberry.api.banking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankService;

import java.util.List;

@Service
public class AlfaBankServiceImpl implements BankService {

    @Override
    public List<Currency> getCurrencies() {
        return null;
    }
}
