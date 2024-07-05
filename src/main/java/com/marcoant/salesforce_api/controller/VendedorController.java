package com.marcoant.salesforce_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoant.salesforce_api.dto.VendedorDTO;
import com.marcoant.salesforce_api.services.VendedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vendedor")
@Tag(name = "Seller Controller", description = "Endpoints for managing sales")
public class VendedorController {
    
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<String> getVendedor() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/list")
    @Operation(summary = "Get all sellers", description = "Retrieve a list of all sellers")
    public ResponseEntity<List<VendedorDTO>> listAll() {
        List<VendedorDTO> sales = vendedorService.getAllVendedores();
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new seller", description = "Create a new seller")
    public ResponseEntity<VendedorDTO> create(@Valid @RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO vendedor = vendedorService.createVendedor(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedor);
    }
}
