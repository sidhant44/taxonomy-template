package com.decathlon.oms.config.exception;

import com.decathlon.oms.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<ErrorDetails> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ErrorDetails errorDetails=new ErrorDetails(new Date(),bodyOfResponse,request.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<ErrorDetails> handleNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { InternalServerEeception.class })
    protected ResponseEntity<ErrorDetails> handleInternalServerException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<ErrorDetails> handleBadRequestException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
