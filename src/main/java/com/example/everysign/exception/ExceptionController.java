package com.example.everysign.exception;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public String bizExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        final List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder res = new StringBuilder();
        for (ObjectError allError : allErrors) {
            res.append(allError.getDefaultMessage());
        }
        return res.toString();
    }

}
