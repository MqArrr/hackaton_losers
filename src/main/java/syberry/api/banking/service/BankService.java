package syberry.api.banking.service;

import org.springframework.stereotype.Service;
import syberry.api.banking.dto.BankDTO;

import java.util.List;

@Service
public interface BankService {
    List<BankDTO> getAll();
}
