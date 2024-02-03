package syberry.api.banking.service;

import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;

import java.util.List;

@Service
public interface BankService {

    List<Currency> getCurrencies();
}
