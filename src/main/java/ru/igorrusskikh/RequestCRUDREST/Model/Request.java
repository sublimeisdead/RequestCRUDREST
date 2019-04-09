
package ru.igorrusskikh.RequestCRUDREST.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Request</b> class.
 * Contains id(number assigns automatically), text description,
 * status(enum: new, in_progress, done, rejected),
 * list of comments
 */
@Data
@Entity
public class Request {
    /**
     * ID variable of Request.
     */
    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "request_id")
    private Long id;

    /**
     * Text description of Request.
     */
    @Getter
    @Setter
    private String description;

    /**
     * Status of Request.
     */
    @Getter
    @Setter
    private RequestStatus status;

    /**
     * List of Comments.
     */
    @Getter
    @Setter
    @OneToMany(targetEntity = Comment.class,
               mappedBy = "request",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    private List<Comment> comment;

    /**
     * Request constructor.
     * @param descriptionParam text description of Request
     * @param statusParam Status of Request
     */
    public Request(final String descriptionParam,
                   final RequestStatus statusParam) {
        this.description = descriptionParam;
        this.status = statusParam;
        this.comment = new ArrayList<>();
    }

    /**
     * Request default constructor.
     */
    public Request() {
    }
}
