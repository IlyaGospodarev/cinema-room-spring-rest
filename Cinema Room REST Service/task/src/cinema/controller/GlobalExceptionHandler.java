package cinema.controller;

import cinema.model.response.exeption.ErrorResponse;
import cinema.model.response.exeption.InvalidPasswordException;
import cinema.model.response.exeption.InvalidTokenException;
import cinema.model.response.exeption.SeatAlreadyPurchasedException;
import cinema.model.response.exeption.SeatNumberOutOfBoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleSeatAlreadyBookedException(SeatAlreadyPurchasedException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleSeatNumberOutOfBoundException(SeatNumberOutOfBoundException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> InvalidPasswordException(InvalidPasswordException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}
