package com.sherlockcodes.libraryDemo.web;


import com.sherlockcodes.libraryDemo.common.LibraryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**

 */

@ControllerAdvice
public class ControllerValidationHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerValidationHandler.class);


    @ExceptionHandler(LibraryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage processValidationError(LibraryException ex) {
        String message = ex.getMessage();
        message = message.replace("\"", "");
        return new ExceptionMessage(message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionMessage handleExceptibu9on(Exception ex) {


        return new ExceptionMessage("INTERNAL SERVER ERROR " + ex.getMessage());
    }


}



