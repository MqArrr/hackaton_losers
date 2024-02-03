package syberry.api.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import syberry.api.banking.controller.exception.BankNotFoundException;
import syberry.api.banking.controller.exception.CurrencyNotFoundException;
import syberry.api.banking.controller.exception.ParamsException;
import syberry.api.banking.controller.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ErrorController {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> handleInternalServerError(RuntimeException e){
//        return ResponseEntity.internalServerError().build();
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
//        return ResponseEntity.notFound().build();
//    }
//
//    @ExceptionHandler(BankNotFoundException.class)
//    public ResponseEntity<?> handleBankNotFoundException(BankNotFoundException e){
//        return ResponseEntity.notFound().build();
//    }
//
//    @ExceptionHandler(CurrencyNotFoundException.class)
//    public ResponseEntity<?> handleBankNotFoundException(CurrencyNotFoundException e){
//        return ResponseEntity.notFound().build();
//    }
//
//    @ExceptionHandler(ParamsException.class)
//    public ResponseEntity<?> handleInternalServerError(ParamsException e){
//        return ResponseEntity.badRequest().build();
//    }
}
