package syberry.api.banking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import syberry.api.banking.entity.Currency;
import syberry.api.banking.service.BankServiceFactory;
import syberry.api.banking.service.impl.BelarusBankServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankServiceFactory bankServiceFactory;

    private final BelarusBankServiceImpl belarusBankServiceService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(belarusBankServiceService.getCurrencies(), HttpStatus.OK);
    }

    @GetMapping("{bankName}/currencies")
    public ResponseEntity<List<Currency>> getCurrenciesByBankName(
            @PathVariable String bankName
    ) {
        var service = bankServiceFactory.createByBankName(bankName);
        return ResponseEntity.ok(service.getCurrencies());
    }

}
