package ru.igorrusskikh.RequestCRUDREST.Advices;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.igorrusskikh.RequestCRUDREST.Exceptions.RequestNotFoundException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
    /**
    * Test class for RequestNotFoundAdvice.
    *
    */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        RequestNotFoundAdvice.class,
        RequestNotFoundAdviceTest.TestConfiguration.class,
        RequestNotFoundAdviceTest.
            RestProcessingExceptionThrowingController.class
        })
public class RequestNotFoundAdviceTest {
    /**
     * Bean of MockMvc class.
     */
    private MockMvc mockMvc;
    /**
    * Bean of WebApplicationContext.
    */
    @Autowired
    private WebApplicationContext wac;
    /**
    * Method invoked before test.
     * @throws Exception .
    */
    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(wac).build();
    }
    /**
    * TestConfiguration class.
    */
    @Configuration
    @EnableWebMvc
    public static class TestConfiguration { }
    /**
     * Rest controller for testing exception.
     */
    @RestController
    @RequestMapping("/tests")
    public static class RestProcessingExceptionThrowingController {
        /**
         * Method works when GET method is invoked.
         * @throws RequestNotFoundException .
         * @return Runtime Exception.
         */
        @GetMapping(value = "/exception")
        public @ResponseBody String find() {
            throw new RequestNotFoundException(null);
        }
    }

    /**
    * Method for testing RequestNotFoundException.
    * @throws Exception .
    */
    @Test
    public void requestNotFoundHandler() throws Exception {

        mockMvc.perform(get("/tests/exception")).
                andExpect(mvcResult -> mvcResult.getResponse().
                    getContentAsString().
                        contains("Could not find request")).
                            andExpect(status().isNotFound());
    }
}
