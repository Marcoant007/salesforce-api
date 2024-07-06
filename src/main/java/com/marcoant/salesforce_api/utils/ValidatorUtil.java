package com.marcoant.salesforce_api.utils;

import java.util.UUID;
import java.util.regex.Pattern;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;

public class ValidatorUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static void validateDocument(CreateVendedorDTO vendedorDTO) {
        if (vendedorDTO.getTipoContratacao() == TipoContratacaoEnum.PJ) {
            if (!DocUtil.isValidCNPJ(vendedorDTO.getCpfCnpj())) {
                throw new IllegalArgumentException("CNPJ inválido");
            }
        } else {
            if (!DocUtil.isValidCPF(vendedorDTO.getCpfCnpj())) {
                throw new IllegalArgumentException("CPF inválido");
            }
        }
    }

    public static String generateMatricula(TipoContratacaoEnum tipoContratacao) {
        String suffix;
        switch (tipoContratacao) {
            case OUTSOURCING:
                suffix = "OUT";
                break;
            case CLT:
                suffix = "CLT";
                break;
            case PJ:
                suffix = "PJ";
                break;
            default:
                throw new IllegalArgumentException("Tipo de contratação inválido");
        }
        
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase() + "-" + suffix;
    }
    
}
