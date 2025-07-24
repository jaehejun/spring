package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;    // 이거 중요!
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void creatProduct_invalidName_returnsError() throws Exception {
        String json = """
                {
                    "name": "",
                    "price":2000
                }
            """;
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateProduct_validId_returnsSuccess() throws Exception {
        String json = """
                {
                    "id": 1,
                    "name": "aa",
                    "price":1000
                }
            """;
        mockMvc.perform(put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Updated : 1"));
    }
}
