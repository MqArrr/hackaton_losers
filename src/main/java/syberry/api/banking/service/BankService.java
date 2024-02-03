package syberry.api.banking.service;

import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.entity.Rate;

import java.time.LocalDate;
import java.util.List;

@Service
public interface BankService {

    List<Currency> getCurrencies();

    List<Rate> getRatesByDate(String currencyCode, LocalDate date);
}
