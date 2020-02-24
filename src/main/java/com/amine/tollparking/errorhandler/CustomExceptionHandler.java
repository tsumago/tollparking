package com.amine.tollparking.errorhandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@SuppressWarnings({"unchecked","rawtypes"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error(ex.getMessage());
        logger.error(ex.getCause().getMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(ApiException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(ApiException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        logger.error(ex.getMessage());
        logger.error(ex.getCause().getMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), details);
        return new ResponseEntity(error, ex.getHttpStatus());
    }
}