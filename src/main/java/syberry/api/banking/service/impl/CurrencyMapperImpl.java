package syberry.api.banking.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import syberry.api.banking.entity.Currency;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyMapperImpl {
    public List<Currency> stringToCurrencyList(String currenciesInString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           return Arrays.asList(objectMapper.readValue(currenciesInString, Currency[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
