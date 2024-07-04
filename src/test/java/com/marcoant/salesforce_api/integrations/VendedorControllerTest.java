package com.marcoant.salesforce_api.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.marcoant.salesforce_api.controller.VendedorController;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetVendedor() throws Exception {
        mockMvc.perform(get("/vendedor"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
