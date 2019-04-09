package ru.igorrusskikh.RequestCRUDREST.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.igorrusskikh.RequestCRUDREST.Exceptions.RequestNotFoundException;
import ru.igorrusskikh.RequestCRUDREST.Model.Comment;
import ru.igorrusskikh.RequestCRUDREST.Model.Request;
import ru.igorrusskikh.RequestCRUDREST.Model.RequestStatus;
import ru.igorrusskikh.RequestCRUDREST.Repository.RequestRepository;

/**
 * Class of REST controller.
 */
@RestController
public class RequestController {
    /**
     * Bean of RequestRepository.
     */
    private final RequestRepository requestRepository;
    /**
     * Constructor of RequestController.
     * @param requestRepo constructor-injected RequestRepository bean.
     */
    RequestController(final RequestRepository requestRepo) {
        this.requestRepository = requestRepo;
    }
    /**
     * Method works when POST method is invoked. Creates new Request.
     * @param description description of new Request.
     * @return created Request.
     */
    @PostMapping(value = "/requests",
                 produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public final ResponseEntity<Request> newRequest(
                                    @RequestBody final String description) {
        Request request = requestRepository.save(new Request(description,
                                                    RequestStatus.NEW));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(request, httpHeaders, HttpStatus.OK);

    }
    /**
     * Method works when PUT method is invoked. Updates status of Request.
     * @param id id of Request.
     * @param newStatus new status of Request.
     */
    @PutMapping(value = "/requests/{id}")
    public final void updateRequestStatus(@RequestBody final RequestStatus
                                       newStatus, @PathVariable final Long id) {

         requestRepository.findById(id)
                .map(request -> {
                    request.setStatus(newStatus);
                    return requestRepository.save(request);
                }).orElseThrow(() -> new RequestNotFoundException(id));
    }
    /**
     * Method works when POST method is invoked. Adds comment to Request.
     * @param newComment added comment to existing Request.
     */
    @PostMapping(value = "/comments")
    public final void addComment(@RequestBody final Comment newComment) {
        requestRepository.findById(newComment.getRequest().getId())
                .map(request -> {
                    request.getComment().add(newComment);
                    return requestRepository.save(request);
                }).orElseThrow(() -> new RequestNotFoundException(newComment.
                                                    getRequest().getId()));

    }
    /**
     * Method works when DELETE method is invoked. Deletes Request.
     * @param id id of deleted Request.
     */
    @DeleteMapping(value = "/requests/{id}")
    public final void deleteRequest(@PathVariable final Long id) {
         requestRepository.deleteById(id);
    }



}
