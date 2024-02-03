package syberry.api.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import syberry.api.banking.service.impl.BankServiceImpl;

@RestController
@RequestMapping("/api/banks")
public class BankController {
    private final BankServiceImpl bankService;

    public BankController(BankServiceImpl bankService) {
        this.bankService = bankService;
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(bankService.getAll(), HttpStatus.OK);
    }
}
