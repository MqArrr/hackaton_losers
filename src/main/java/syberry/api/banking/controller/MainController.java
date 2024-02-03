package syberry.api.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/main")
public class MainController {
    @GetMapping("https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates")
    public ResponseEntity<String> getCurrenciesAlfa() {
        return null;
    }

    @GetMapping("https://api.nbrb.by/exrates/currencies")
    public ResponseEntity<String> getCurrenciesNBRB() {
        return null;
    }

    @GetMapping("https://belarusbank.by/api/kurs_cards")
    public ResponseEntity<String> getCurrenciesBel() {
        return null;
    }

    @GetMapping("https://api.nbrb.by/exrates/rates/{id}")
    public ResponseEntity<String> getRatesNBRB(@PathVariable Long id, @RequestParam String ondate) {
        return null;
    }

    @GetMapping("https://api.nbrb.by/exrates/rates/dynamics/{id}")
    public ResponseEntity<String> getDynamic(@PathVariable Long id, @RequestParam String startdate, @RequestParam String enddate) {
        return null;
    }
}
