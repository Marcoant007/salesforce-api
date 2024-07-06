package com.marcoant.salesforce_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.ListVendedorDTO;
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
    public ResponseEntity<List<CreateVendedorDTO>> listAll() {
        List<CreateVendedorDTO> sales = vendedorService.getAllVendedores();
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new seller", description = "Create a new seller")
    public ResponseEntity<CreateVendedorDTO> create(@Valid @RequestBody CreateVendedorDTO vendedorDTO) {
        CreateVendedorDTO vendedor = vendedorService.createVendedor(vendedorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedor);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a seller", description = "Update an existing seller")
    public ResponseEntity<CreateVendedorDTO> update(@PathVariable Long id, @Valid @RequestBody CreateVendedorDTO vendedorDTO) {
        CreateVendedorDTO updatedVendedor = vendedorService.updateVendedor(id, vendedorDTO);
        return ResponseEntity.ok(updatedVendedor);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a seller", description = "Delete an existing seller")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/matricula/{matricula}/filial")
    public ResponseEntity<ListVendedorDTO> getVendedorWithFilial(@PathVariable String matricula) {
        ListVendedorDTO listVendedorDTO = vendedorService.getVendedorWithFilial(matricula);
        return ResponseEntity.ok(listVendedorDTO);
    }

    @GetMapping("/matricula/{matricula}/filial")
    @Operation(summary = "Get a seller with their filial by matricula", description = "Retrieve a seller and their filial by their matricula")
    public ResponseEntity<CreateVendedorDTO> getById(@PathVariable Long id) {
        CreateVendedorDTO vendedor = vendedorService.getVendedorById(id);
        return ResponseEntity.ok(vendedor);
    }
}
