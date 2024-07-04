package com.marcoant.salesforce_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcoant.salesforce_api.repository.VendedorRepository;

@Service
public class VendedorService {
    
    @Autowired
    private VendedorRepository vendedorRepository;

    
}
