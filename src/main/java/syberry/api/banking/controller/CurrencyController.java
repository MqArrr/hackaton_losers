package syberry.api.banking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import syberry.api.banking.service.impl.CurrencyServiceImpl;


@RestController
@RequestMapping("/api/banks/currencies")
public class CurrencyController {
    private final CurrencyServiceImpl currencyService;

    public CurrencyController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping()
    public ResponseEntity<?> handleGetAll() throws JsonProcessingException {
        currencyService.getAll();
        return new ResponseEntity<>(currencyService.getAll(), HttpStatus.OK);
    }
}
