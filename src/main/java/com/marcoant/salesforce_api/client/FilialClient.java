package com.marcoant.salesforce_api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.marcoant.salesforce_api.dto.FilialDTO;

@Component
public class FilialClient {

    @Value("${filial.api.url}")
    private String filialApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public FilialDTO getFilialById(Long id) {
        String url = filialApiUrl + id;
        ResponseEntity<FilialDTO> response = restTemplate.getForEntity(url, FilialDTO.class);
        return response.getBody();
    }
}
