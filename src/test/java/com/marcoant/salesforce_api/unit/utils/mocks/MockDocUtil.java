package com.marcoant.salesforce_api.unit.utils.mocks;

public class MockDocUtil {
    public static boolean isValidCPF(String cpf) {
        return cpf != null && cpf.equals("12345678909");
    }

    public static boolean isValidCNPJ(String cnpj) {
        return cnpj != null && cnpj.equals("12345678000199");
    }
}
