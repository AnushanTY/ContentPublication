package com.zerobeta.contentpublication.exception;

import com.zerobeta.contentpublication.domain.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Response> handleHttpStatusCodeExceptionException(HttpStatusCodeException httpStatusCodeException){
        logger.error(" ContentPublicationApplication RuntimeException :: {}", httpStatusCodeException.getMessage());

        return new ResponseEntity<>(Response.failure(httpStatusCodeException.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleRuntimeException(RuntimeException runtimeException){
        logger.error(" ContentPublicationApplication RuntimeException :: {}", runtimeException.getMessage());

        return new ResponseEntity<>(Response.failure(runtimeException.getMessage()), HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception exception){
        logger.error(" ContentPublicationApplication Exception :: {}", exception.getMessage());

        return new ResponseEntity<>(Response.failure(exception.getMessage()), HttpStatus.OK);
    }
}
