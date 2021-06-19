package hello.admincontrol.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hello.admincontrol.exception.BaseException;


@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public Object processException(BaseException ex) {
        return ex;
    }
}

