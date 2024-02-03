package syberry.api.banking.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import syberry.api.banking.dto.BankDTO;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public List<BankDTO> getAll() {
        RestTemplate template = new RestTemplate();
        Currency currency = new Currency();
        String url = "https://api.nbrb.by/exrates/currencies";
        ObjectMapper objectMapper = new ObjectMapper();
//        ArrayList<Currency> currencies = template.getForObject(url, Currency.class);
//        System.out.println(currencies);
//        return Arrays.asList(objectMapper.readValue(jsonArray, Currency[].class));
    return null;
    }
}
