package syberry.api.banking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankService;

import java.util.List;

@Service("NATIONALBANK")
@RequiredArgsConstructor
public class NationalBankServiceImpl implements BankService {
    @Value("{url.currencies.national-bank}")
    public static String url;
    private final CurrencyMapperImpl currencyMapperImpl;
    private final ExternalService externalService;

    @Override
    public List<Currency> getCurrencies() {
        var currenciesInString = externalService.getCurrencies(url);
        var currenciesList = currencyMapperImpl.stringToCurrencyList(currenciesInString);
        return currenciesList;
    }
}
