package com.raf.usermanagmentsystem.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(getErrorsMap(errors));

    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, List<String>>> handleJpaExceptions(TransactionSystemException exception){
        return ResponseEntity.badRequest().body(getErrorsMap(Arrays.asList(exception.getMessage())));

    }



    @ExceptionHandler(PrivilegeNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handlePrivilegeError(PrivilegeNotFoundException exception){
        return ResponseEntity.badRequest().body(getErrorsMap(Arrays.asList(exception.getMessage())));
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors){
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
