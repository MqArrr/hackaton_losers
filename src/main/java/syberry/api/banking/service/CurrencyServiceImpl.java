package syberry.api.banking.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import syberry.api.banking.entity.Currency;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyServiceImpl {
    public List<Currency> getAllByBankName() throws JsonProcessingException {
        RestTemplate template = new RestTemplate();
        String url = "https://api.nbrb.by/exrates/currencies";
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(template.getForObject(url, String.class), Currency[].class));
    }
}
