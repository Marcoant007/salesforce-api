package com.marcoant.salesforce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoant.salesforce_api.client.FilialClient;
import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.services.interfaces.IFilialService;

@Service
public class FilialService implements IFilialService {
    
    @Autowired
    private FilialClient filialClient;

    public FilialDTO getFilialById(Long id) {
        FilialDTO filial = filialClient.getFilialById(id);
        if (filial == null || !filial.getAtivo()) {
            throw new IllegalArgumentException("Filial não encontrada ou inativa");
        }
        return filial;
    }
}
