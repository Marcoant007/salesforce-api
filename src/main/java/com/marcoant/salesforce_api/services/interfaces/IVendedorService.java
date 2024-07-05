package com.marcoant.salesforce_api.services.interfaces;

import java.util.List;

import com.marcoant.salesforce_api.dto.VendedorDTO;

public interface IVendedorService {
    VendedorDTO createVendedor(VendedorDTO vendedorDTO);
    VendedorDTO updateVendedor(Long id, VendedorDTO vendedorDTO);
    void deleteVendedor(Long id);
    VendedorDTO getVendedorById(Long id);
    List<VendedorDTO> getAllVendedores();
}
