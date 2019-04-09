package ru.igorrusskikh.RequestCRUDREST.Exceptions;
/**
 * Exception thrown when Request is not exist.
 */
public class RequestNotFoundException extends RuntimeException {
    /**
     * RequestNotFoundException constructor.
     * @param id of Request that not exist
     */
    public RequestNotFoundException(final Long id) {
        super("Could not find request " + id);
    }
}
