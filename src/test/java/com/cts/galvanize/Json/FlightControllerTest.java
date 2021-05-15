package com.cts.galvanize.Json;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetFlight() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/flights/flight")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)));
    }

    @Test
    public void testGetFlights() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/flights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)));
    }


//    @Test
//    public void testObject() throws Exception {
//        this.mockMvc.perform(
//                get("/json/simple-object")
//                        .accept(MediaType.APPLICATION_JSON_UTF8)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName", is("Dwayne")))
//                .andExpect(jsonPath("$.lastName", is("Johnson")));
//    }
}
