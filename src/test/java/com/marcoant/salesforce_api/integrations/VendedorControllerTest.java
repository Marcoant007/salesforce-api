package com.marcoant.salesforce_api.integrations;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcoant.salesforce_api.controller.VendedorController;
import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.services.VendedorService;
@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

     @Test
    public void testListAll() throws Exception {
        CreateVendedorDTO vendedor = new CreateVendedorDTO();
        vendedor.setNome("Test Vendedor");
        List<CreateVendedorDTO> allVendedores = Collections.singletonList(vendedor);

        given(vendedorService.getAllVendedores()).willReturn(allVendedores);

        mockMvc.perform(get("/vendedor/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Test Vendedor"));
    }
}
