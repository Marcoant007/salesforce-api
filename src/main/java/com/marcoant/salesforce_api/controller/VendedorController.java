package com.marcoant.salesforce_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorController {
    
    // @Autowired
    // private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<String> getVendedor() {
        return ResponseEntity.ok("Hello World!");
    }
}
