package syberry.api.banking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankService;

import java.util.List;

@Service("BELARUSBANK")
@RequiredArgsConstructor
public class BelarusBankServiceImpl implements BankService {
    @Value("${url.currencies.belarus-bank}")
    public String url;
    private final CurrencyMapperImpl currencyMapper;
    private final ExternalService externalService;

    @Override
    public List<Currency> getCurrencies() {
        var currencies = externalService.getCurrencies(url);
        var listCurrencies = currencyMapper.stringToCurrencyList(currencies);
        return listCurrencies;
    }

}
