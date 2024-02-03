package syberry.api.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.entity.EBankName;
import syberry.api.banking.entity.Rate;
import syberry.api.banking.service.BankServiceFactory;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankServiceFactory bankServiceFactory;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(EBankName.values(), HttpStatus.OK);
    }

    @GetMapping("{bankName}/currencies")
    public ResponseEntity<List<Currency>> getCurrenciesByBankName(
            @PathVariable String bankName
    ) {
        var service = bankServiceFactory.createByBankName(bankName);
        return ResponseEntity.ok(service.getCurrencies());
    }

    @GetMapping("/rate")
    public ResponseEntity<List<Rate>> getCurrenciesByDate(
            @RequestParam String currencyCode,
            @RequestParam String bank,
            @RequestParam String date
    ) {
        var service = bankServiceFactory.createByBankName(bank);
        return ResponseEntity.ok(service.getRatesByDate(currencyCode, LocalDate.parse(date)));
    }

}
