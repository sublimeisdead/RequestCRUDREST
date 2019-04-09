package ru.igorrusskikh.RequestCRUDREST.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.igorrusskikh.RequestCRUDREST.Model.Comment;
import ru.igorrusskikh.RequestCRUDREST.Model.Request;
import ru.igorrusskikh.RequestCRUDREST.Model.RequestStatus;
import ru.igorrusskikh.RequestCRUDREST.Repository.RequestRepository;
import ru.igorrusskikh.RequestCRUDREST.RequestCrudRestApplication;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for RequestController.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RequestCrudRestApplication.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RequestControllerIntegrationTest {

    /**
     * Bean of WebApplicationContext.
     */
    @Autowired
    private WebApplicationContext wac;
    /**
     * MockMvc field.
     */
    private MockMvc mockMvc;
    /**
     * Bean of RequestRepository.
     */
    @Autowired
    private RequestRepository repository;

    /** Method works before tests.
     * @throws Exception .
     */
    @Before
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }
    /**
     * newRequest() test method.
     * @throws Exception .
     */
    @Test
    public void newRequest() throws Exception {

        this.mockMvc.perform(post("/requests").
                content("Test request description").
                contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
                characterEncoding("utf-8"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content()
                .contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").exists());
    }
    /**
     * updateRequestStatus() test method.
     *
     */
    @Test
    public void updateRequestStatus() {
        try {

            Request savedRequest = repository.save(
                new Request("Test request description", RequestStatus.NEW));


            this.mockMvc.perform(put("/requests/" + savedRequest.getId()).
                   content("\"DONE\"").
                    contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
                    characterEncoding("utf-8"))
                    .andDo(print()).
                    andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * addComment() test method.
     *
     */
    @Test
    public void addComment() {
            Request savedRequest = repository.save(
                new Request("Test request description", RequestStatus.NEW));
        Comment comment = new Comment(1L, "Test Comment", savedRequest);
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(comment);
            this.mockMvc.perform(post("/comments").
                    content(json).
                    contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).
                    characterEncoding("utf-8"))
                    .andDo(print()).andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * deleteRequest() test method.
     *
     */
    @Test
    public void deleteRequest() {
        try {
            Request savedRequest = repository.save(
                new Request("Test request description", RequestStatus.NEW));

            this.mockMvc.perform(delete("/requests/" + savedRequest.getId()))
                    .andDo(print()).
                    andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Make sure that bean are not null.
     *
     */
    @Test
    public void givenWacWhenServletContextThenItProvidesRequestController() {
        ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(wac.getBean("requestController"));
        Assert.assertNotNull(wac.getBean("requestRepository"));
    }
}
