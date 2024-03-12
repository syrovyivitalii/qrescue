package ua.QRescue.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class NotFoundException extends RuntimeException {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        // Log the exception
        // Return a 401 Unauthorized response
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
