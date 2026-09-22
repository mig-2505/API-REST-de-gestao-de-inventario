package dev.miguel.inventarioapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Intercepta erros (@Valid do DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Pega todos os campos que falharam e suas mensagens (@NotBlank, @Positive)
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Devolve status 400 (Bad Request) com a lista de erros bonitinha
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Intercepta erros das regras de negócio (ex: Estoque Insuficiente)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessExceptions(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("erro", ex.getMessage()); // Pega a mensagem que escrevemos lá no Service

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}