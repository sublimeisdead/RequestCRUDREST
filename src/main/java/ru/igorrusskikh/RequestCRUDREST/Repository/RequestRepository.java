package ru.igorrusskikh.RequestCRUDREST.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.igorrusskikh.RequestCRUDREST.Model.Request;

/**
 * RequestRepository perform CRUD operations.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
}
