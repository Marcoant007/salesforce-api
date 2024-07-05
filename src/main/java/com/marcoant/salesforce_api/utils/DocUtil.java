package com.marcoant.salesforce_api.utils;

import java.util.regex.Pattern;

public class DocUtil {

    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{14}");

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || !CPF_PATTERN.matcher(cpf).matches() || hasAllSameDigits(cpf)) {
            return false;
        }
        return isValidCPFFormat(cpf);
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || !CNPJ_PATTERN.matcher(cnpj).matches() || hasAllSameDigits(cnpj)) {
            return false;
        }
        return isValidCNPJFormat(cnpj);
    }

    private static boolean isValidCPFFormat(String cpf) {
        int[] weights1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        return isValidDocument(cpf.substring(0, 9), weights1, cpf.charAt(9) - '0') && 
               isValidDocument(cpf.substring(0, 10), weights2, cpf.charAt(10) - '0');
    }

    private static boolean isValidCNPJFormat(String cnpj) {
        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        return isValidDocument(cnpj.substring(0, 12), weights1, cnpj.charAt(12) - '0') && 
               isValidDocument(cnpj.substring(0, 13), weights2, cnpj.charAt(13) - '0');
    }

    private static boolean isValidDocument(String document, int[] weights, int checkDigit) {
        int sum = 0;
        for (int i = 0; i < document.length(); i++) {
            sum += (document.charAt(i) - '0') * weights[i];
        }
        int mod = sum % 11;
        int calculatedCheckDigit = (mod < 2) ? 0 : 11 - mod;
        return calculatedCheckDigit == checkDigit;
    }

    private static boolean hasAllSameDigits(String document) {
        char firstDigit = document.charAt(0);
        for (int i = 1; i < document.length(); i++) {
            if (document.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }
}
