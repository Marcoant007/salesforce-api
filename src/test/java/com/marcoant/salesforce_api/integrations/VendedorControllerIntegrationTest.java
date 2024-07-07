package com.marcoant.salesforce_api.integrations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.repository.VendedorRepository;
import com.marcoant.salesforce_api.unit.services.mocks.VendedorMock;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VendedorControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Test
    @Transactional
    public void testCreateVendedor() throws Exception {
        CreateVendedorDTO vendedorDTO = VendedorMock.createVendedorDTO();

        mockMvc.perform(post("/vendedor/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(vendedorDTO.getNome()));
    }

    @Test
    @Transactional
    public void testUpdateVendedor() throws Exception {
        CreateVendedorDTO vendedorDTO = VendedorMock.createVendedorDTO();
        Vendedor savedVendedor = vendedorRepository.save(VendedorMock.vendedor());
        vendedorDTO.setId(savedVendedor.getId());

        mockMvc.perform(put("/vendedor/update/{id}", savedVendedor.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(vendedorDTO.getNome()))
                .andExpect(jsonPath("$.email").value(vendedorDTO.getEmail()))
                .andExpect(jsonPath("$.cpfCnpj").value(vendedorDTO.getCpfCnpj()));
    }

    @Test
    @Transactional
    public void testDeleteVendedor() throws Exception {
        Vendedor savedVendedorDTO = vendedorRepository.save(VendedorMock.vendedor());

        mockMvc.perform(delete("/vendedor/delete/{id}", savedVendedorDTO.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void testListWithFilial() throws Exception {
        Vendedor savedVendedor = vendedorRepository.save(VendedorMock.vendedor());
        ListVendedorDTO listVendedorDTO = new ListVendedorDTO();
        listVendedorDTO.setMatricula(savedVendedor.getMatricula());
        listVendedorDTO.setNome(savedVendedor.getNome());

        mockMvc.perform(get("/vendedor/matricula/{matricula}/filial", savedVendedor.getMatricula()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matricula").value(savedVendedor.getMatricula()))
                .andExpect(jsonPath("$.nome").value(savedVendedor.getNome()));
    }

    @Test
    @Transactional
    public void testListAll() throws Exception {
        Vendedor savedVendedor = vendedorRepository.save(VendedorMock.vendedor());

        mockMvc.perform(get("/vendedor/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value(savedVendedor.getNome()));
    }

}
