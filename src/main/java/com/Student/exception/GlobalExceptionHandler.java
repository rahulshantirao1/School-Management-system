package com.Student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>>resourceNotFoundExceptionResponseEntity(ResourceNotFoundException r,WebRequest request){
        Map<String,Object>message= new HashMap<>();
        message.put("Exception",r.getMessage());
        message.put("Url",request.getDescription(false));
        message.put("DateAndTime",new Date());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>globalExceptionHandler(Exception r){

        return new ResponseEntity<>(r.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
