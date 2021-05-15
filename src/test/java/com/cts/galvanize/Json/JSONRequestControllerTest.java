package com.cts.galvanize.Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JSONRequestController.class)
public class JSONRequestControllerTest {

    @Autowired
    private MockMvc mvc;

    ObjectMapper objectMapper = new ObjectMapper();                    // 1


//    You can use .contentType(MediaType.APPLICATION_JSON) to set the content type correctly
//    You use .content() to fill in the body

    @Test
    public void testObjectParams() throws Exception {
        MockHttpServletRequestBuilder request = post("/jr/object-example")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"q\": \"other\", \"from\": \"2010\"}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Search: q=something from=2008"));
    }


    @Test
    public void testCreate() throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>(){  // 2
            {
                put("name", "Hercules");
                put("age", 57);
            }
        };

        String json = objectMapper.writeValueAsString(data);            // 3

        MockHttpServletRequestBuilder request = post("/some/path")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);                                         // 4

        this.mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void testRawBody() throws Exception {
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/jr/string-example")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(json));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
