package test.java.testjava.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.java.testjava.controller.pojo.Message;

import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        String errorMessage=ex.getMessage();
        return handleExceptionInternal(ex, new Message(errorMessage), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }


    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<Object> handleIOException(RuntimeException ex, WebRequest request) {

        String errorMessage=ex.getMessage();
        return handleExceptionInternal(ex, new Message(errorMessage), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
