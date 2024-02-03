package syberry.api.banking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class BaseBankService {
    private final CurrencyMapperImpl currencyMapperImpl;
    private final ExternalService externalService;

    public List<Currency> getCurrencies(String url) {
        var currenciesInString = externalService.getCurrencies(url);
       return currencyMapperImpl.stringToCurrencyList(currenciesInString);

    }

    public List<Currency> getCurrenciesByDate(String url, String currencyCode, Date date) {
        return getCurrencies(url).stream()
                .filter(currency -> currency.getCurCode().equals(currencyCode) && currency.getCurDateStart().equals(date))
                .collect(Collectors.toList());
    }
}
