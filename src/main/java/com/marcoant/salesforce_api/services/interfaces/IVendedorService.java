package com.marcoant.salesforce_api.services.interfaces;

import java.util.List;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;

public interface IVendedorService {
    CreateVendedorDTO createVendedor(CreateVendedorDTO vendedorDTO);
    CreateVendedorDTO updateVendedor(Long id, CreateVendedorDTO vendedorDTO);
    void deleteVendedor(Long id);
    List<CreateVendedorDTO> getAllVendedores();
    ListVendedorDTO getVendedorWithFilial(String matricula);
}
