package com.marcoant.salesforce_api.enums;

import com.marcoant.salesforce_api.exceptions.ApiException;

public enum TipoContratacaoEnum {
    OUTSOURCING,
    CLT,
    PJ;

    private static final int HTTP_STATUS_BAD_REQUEST = 400;

    public static TipoContratacaoEnum fromString(String value) {
        for (TipoContratacaoEnum tipoContratacao : TipoContratacaoEnum.values()) {
            if (tipoContratacao.toString().equals(value)) {
                return tipoContratacao;
            }
        }
        throw new ApiException("Tipo de contratação inválido", HTTP_STATUS_BAD_REQUEST);
    }
}
