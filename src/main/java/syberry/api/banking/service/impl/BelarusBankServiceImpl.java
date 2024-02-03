package syberry.api.banking.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.entity.Rate;
import syberry.api.banking.service.BankService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("BELARUSBANK")
@RequiredArgsConstructor
public class BelarusBankServiceImpl implements BankService {
    @Value("${url.currencies.belarus-bank}")
    public String url;
    private final CurrencyMapperImpl currencyMapperImpl;
    private final ExternalService externalService;

    public List<Currency> getCurrencies() {
        var currenciesInString = externalService.getCurrencies(url);
        var currenciesList = currencyMapperImpl.stringToCurrencyList(currenciesInString, "BELARUSBANK");
        return currenciesList;
    }

    @Override
    public List<Rate> getRatesByDate(String currencyCode, LocalDate date) {
        var currenciesInString = externalService.getCurrencies("https://api.nbrb.by/exrates/rates/"+ currencyCode + "?ondate=" + date);
        var currenciesList = currencyMapperImpl.stringToRateList(currenciesInString);
        return currenciesList;
    }

}
