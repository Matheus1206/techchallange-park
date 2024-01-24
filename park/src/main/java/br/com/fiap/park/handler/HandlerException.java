package br.com.fiap.park.handler;

import br.com.fiap.park.config.CarNotParkingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class HandlerException {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(MethodArgumentNotValidException exception){
        return exception.getBindingResult().getFieldError().getDefaultMessage();
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String noSuchElementException(NoSuchElementException exception){
        return exception.getMessage();
    }

    @ExceptionHandler({CarNotParkingException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String carNotParkingException(CarNotParkingException exception){
        return exception.getMessage();
    }
}
