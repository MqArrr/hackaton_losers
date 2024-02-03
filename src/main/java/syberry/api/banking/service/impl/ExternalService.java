package syberry.api.banking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    public String getCurrencies(String url) {
        RestTemplate template = new RestTemplate();
        var object = template.getForObject(url, String.class);
        return object;

    }
}
