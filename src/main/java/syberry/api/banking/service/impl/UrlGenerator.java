package syberry.api.banking.service.impl;

import java.util.Date;

public class UrlGenerator {


    public String generate(String baseUrl, String currencyCode, Date date) {
        return baseUrl + "?";
    }
}
