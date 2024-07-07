package com.marcoant.salesforce_api.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;
import com.marcoant.salesforce_api.services.VendedorService;
import com.marcoant.salesforce_api.unit.services.mocks.VendedorMock;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateVendedor() throws Exception {
        CreateVendedorDTO vendedorDTO = VendedorMock.createVendedorDTO();
        CreateVendedorDTO savedVendedorDTO = VendedorMock.createVendedorDTO();

        given(vendedorService.createVendedor(Mockito.any(CreateVendedorDTO.class))).willReturn(savedVendedorDTO);

        mockMvc.perform(post("/vendedor/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(vendedorDTO.getNome()))
                .andExpect(jsonPath("$.matricula").value(vendedorDTO.getMatricula()))
                .andExpect(jsonPath("$.email").value(vendedorDTO.getEmail()))
                .andExpect(jsonPath("$.cpfCnpj").value(vendedorDTO.getCpfCnpj()));
            }

    @Test
    public void testUpdateVendedor() throws Exception {
        CreateVendedorDTO vendedorDTO = VendedorMock.createVendedorDTO();
        CreateVendedorDTO updatedVendedorDTO = VendedorMock.createVendedorDTO();

        given(vendedorService.updateVendedor(Mockito.anyLong(), Mockito.any(CreateVendedorDTO.class))).willReturn(updatedVendedorDTO);

        mockMvc.perform(put("/vendedor/update/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(vendedorDTO.getNome()))
                .andExpect(jsonPath("$.matricula").value(vendedorDTO.getMatricula()))
                .andExpect(jsonPath("$.email").value(vendedorDTO.getEmail()))
                .andExpect(jsonPath("$.cpfCnpj").value(vendedorDTO.getCpfCnpj()));
    }

    @Test
    public void testDeleteVendedor() throws Exception {
        Mockito.doNothing().when(vendedorService).deleteVendedor(Mockito.anyLong());
        mockMvc.perform(delete("/vendedor/delete/{id}", 1L)).andExpect(status().isNoContent());
    }

    @Test
    public void testListWithFilial() throws Exception {
        ListVendedorDTO listVendedorDTO = new ListVendedorDTO();
        listVendedorDTO.setMatricula("12345678-CLT");
        listVendedorDTO.setNome("Test Vendedor");

        given(vendedorService.getVendedorWithFilial(Mockito.anyString())).willReturn(listVendedorDTO);

        mockMvc.perform(get("/vendedor/matricula/{matricula}/filial", "12345678-CLT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value("12345678-CLT"))
                .andExpect(jsonPath("$.nome").value("Test Vendedor"));
    }

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
