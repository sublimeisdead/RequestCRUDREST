package ru.igorrusskikh.RequestCRUDREST.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.igorrusskikh.RequestCRUDREST.Exceptions.RequestNotFoundException;
    /**
    * RequestNotFoundAdvice works when expected Request is not found.
    * Throws RequestNotFoundException.
    */
@ControllerAdvice
public class RequestNotFoundAdvice {
    /**
     * Handler method of RequestNotFoundAdvice.
     * @param ex RequestNotFoundException thrown when advice works
     * @return message of RequestNotFoundException
     */
    @ResponseBody
    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    final String requestNotFoundHandler(final RequestNotFoundException ex) {
        return ex.getMessage();
    }
}
