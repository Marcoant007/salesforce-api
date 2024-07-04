package com.marcoant.salesforce_api.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.entity.Filial;
import com.marcoant.salesforce_api.exceptions.ApiException;
import com.marcoant.salesforce_api.repository.FilialRepository;

import jakarta.transaction.Transactional;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    @Transactional
    public FilialDTO createFilialDTO(FilialDTO filialDTO) {
        try {
            Filial filial = new Filial();
            filial.setNome(filialDTO.getNome());
            filial.setCnpj(filialDTO.getCnpj());
            filial.setAtivo(filialDTO.getAtivo());
            filial.setCidade(filialDTO.getCidade());
            filial.setDataCadastro(LocalDateTime.now());
            filial.setTipo(filialDTO.getTipo());
            filial.setUf(filialDTO.getUf());
            Filial savedFilial = filialRepository.save(filial);
            return FilialDTO.fromFilialDTO(savedFilial);
        } catch (Exception e) {
            throw new ApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
