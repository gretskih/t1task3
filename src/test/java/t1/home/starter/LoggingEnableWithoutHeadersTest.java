package t1.home.starter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(properties = {
        "logging.enabled=true",
        "logging.log-level=info",
        "logging.request-method=true",
        "logging.request-url=true",
        "logging.request-headers=false",
        "logging.request-param=true",
        "logging.response-status=true",
        "logging.response-headers=false",
        "logging.response-processing-time=true"
})
@AutoConfigureMockMvc
public class LoggingEnableWithoutHeadersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void logControllerTest() throws Exception {

        this.mockMvc.perform(get("/test?name=natali")
                        .header("Test-Header", "TestValue"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("natali"))
                .andExpect(jsonPath("$.test").value("true"));
    }

}
