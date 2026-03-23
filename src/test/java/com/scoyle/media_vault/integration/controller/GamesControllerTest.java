package com.scoyle.media_vault.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoyle.media_vault.request.CreateGameRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GamesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenEmptyRequest_thenReturnStatus400() throws Exception {
        CreateGameRequest request = new CreateGameRequest();
        String body = objectMapper.writeValueAsString(request);

        MvcResult result = mvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        System.out.println("Result: " + result.getResponse().getContentAsString());

        MockHttpServletResponse resp = result.getResponse();
        assertEquals(400, resp.getStatus());
    }

    @Test
    void givenEmptyRequestBody_thenReturnStatus400() throws Exception {

        MvcResult result = mvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn();

        System.out.println("Result: " + result.getResponse().getContentAsString());

        MockHttpServletResponse resp = result.getResponse();
        assertEquals(400, resp.getStatus());
    }
}
