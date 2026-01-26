package com.project.springboot_api_test.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(int status, String error, String message) { }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {

        ErrorResponse error = new ErrorResponse(400, "Bad Request", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalCallerException.class)
    public ResponseEntity<ErrorResponse> handleIllegalCallerException(IllegalCallerException e) {
        ErrorResponse error = new ErrorResponse(403, "Forbidden", e.getMessage());
        return ResponseEntity.status(403).body(error);
    }

    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public ResponseEntity<ErrorResponse> handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e) {
        ErrorResponse error = new ErrorResponse(404, "Resource Not Found", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(500, "Internal Server Error", e.getMessage());
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleEnumConversionError(
            MethodArgumentTypeMismatchException e
    ) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(
                        400,
                        "Bad Request",
                        "Invalid value for parameter " + e.getName() + ": " + e.getValue()
                ));
    }
}
