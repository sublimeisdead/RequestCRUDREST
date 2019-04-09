
package ru.igorrusskikh.RequestCRUDREST.Model;

/**
 * Lombok annotations for getter/setter generation.
 */
import lombok.Getter;
import lombok.Setter;

/**
 * JPA annotations for Comment entity.
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * <b>Comment</b> class.
 */
@Entity
public class Comment {

    /**
     * ID variable of Comment.
     */
    @Id
    @Getter
    @Setter
    private Long id;

    /**
     * String description of Comment.
     */
    @Getter
    @Setter
    private String text;

    /**
     * Request Comment.
     */
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;



    /**
     * Public constructor.
     * @param idParam ID variable of Comment
     * @param textParam String description of Comment
     * @param requestParam Request comment
     */
    public Comment(final Long idParam,
                   final String textParam,
                   final Request requestParam) {
        this.id = idParam;
        this.text = textParam;
        this.request = requestParam;
    }

    /**
     * Public default constructor.
     */
    public Comment() {
    }
}
