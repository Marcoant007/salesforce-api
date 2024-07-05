package com.marcoant.salesforce_api.services.interfaces;

import com.marcoant.salesforce_api.dto.FilialDTO;

public interface IFilialService {
    FilialDTO getFilialById(Long id);
}