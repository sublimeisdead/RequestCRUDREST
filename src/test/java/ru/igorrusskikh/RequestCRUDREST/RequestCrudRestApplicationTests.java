package ru.igorrusskikh.RequestCRUDREST;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.igorrusskikh.RequestCRUDREST.Controller.RequestController;
import ru.igorrusskikh.RequestCRUDREST.Model.Request;
import ru.igorrusskikh.RequestCRUDREST.Repository.RequestRepository;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * <b>RequestCrudRestApplicationTests</b> class
 * that check if beans of Context are loaded.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RequestCrudRestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RequestCrudRestApplicationTests {

    /**
     * Bean of <b>RequestController</b> class.
     */
    @Autowired
    private RequestController requestController;

    /**
     * Bean of <b>RequestRepository</b> interface.
     */
    @Autowired
    private RequestRepository requestRepository;

    /**
     * Test method of <b>RequestController</b>,
     * <b>RequestRepository</b> beans are loaded.
     */
    @Test
    public void contextLoads() {
        assertThat(requestController).isNotNull();
        assertThat(requestRepository).isNotNull();
    }
}
