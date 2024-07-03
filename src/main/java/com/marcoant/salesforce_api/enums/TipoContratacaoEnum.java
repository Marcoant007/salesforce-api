package com.marcoant.salesforce_api.enums;

import com.marcoant.salesforce_api.exceptions.ApiException;

public enum TipoContratacaoEnum {
    OUTSOURCING,
    CLT,
    PESSOA_JURIDICA;

    public static TipoContratacaoEnum fromString(String value) {
        for(TipoContratacaoEnum tipoContratacao : TipoContratacaoEnum.values()) {
            if(tipoContratacao.toString().equals(value)) {
                return tipoContratacao;
            }
        }
        throw new ApiException("Tipo de contratação inválido", 400);
    }
}
