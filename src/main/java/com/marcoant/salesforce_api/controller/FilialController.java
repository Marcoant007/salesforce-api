package com.marcoant.salesforce_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.services.FilialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/filial")
public class FilialController {
    
    @Autowired
    private FilialService filialService;


    @PostMapping
    public ResponseEntity<FilialDTO> createFilialDTO(@Valid @RequestBody FilialDTO filialDTO) {
        return ResponseEntity.ok(filialService.createFilialDTO(filialDTO));
    }
}
