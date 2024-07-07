package com.marcoant.salesforce_api.unit.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;
import com.marcoant.salesforce_api.utils.DocUtil;
import com.marcoant.salesforce_api.utils.ValidatorUtil;

public class ValidatorUtilTest {

     @Test
    public void testIsValidEmail() {
        assertTrue(ValidatorUtil.isValidEmail("test@example.com"));
        assertFalse(ValidatorUtil.isValidEmail("invalid-email"));
    }

    @Test
    public void testValidateDocumentWithValidCPF() {
        CreateVendedorDTO vendedorDTO = new CreateVendedorDTO();
        vendedorDTO.setTipoContratacao(TipoContratacaoEnum.CLT);
        vendedorDTO.setCpfCnpj("12345678909");

        assertTrue(DocUtil.isValidCPF(vendedorDTO.getCpfCnpj()));

        ValidatorUtil.validateDocument(vendedorDTO);
    }

    @Test
    public void testValidateDocumentWithInvalidCPF() {
        CreateVendedorDTO vendedorDTO = new CreateVendedorDTO();
        vendedorDTO.setTipoContratacao(TipoContratacaoEnum.CLT);
        vendedorDTO.setCpfCnpj("invalid-cpf");

        assertFalse(DocUtil.isValidCPF(vendedorDTO.getCpfCnpj()));

        assertThrows(IllegalArgumentException.class, () -> {
            ValidatorUtil.validateDocument(vendedorDTO);
        }, "CPF inválido");
    }

    @Test
    public void testValidateDocumentWithValidCNPJ() {
        CreateVendedorDTO vendedorDTO = new CreateVendedorDTO();
        vendedorDTO.setTipoContratacao(TipoContratacaoEnum.PJ);
        vendedorDTO.setCpfCnpj("04616020000142");

        assertTrue(DocUtil.isValidCNPJ(vendedorDTO.getCpfCnpj()));

        ValidatorUtil.validateDocument(vendedorDTO);
    }

    @Test
    public void testValidateDocumentWithInvalidCNPJ() {
        CreateVendedorDTO vendedorDTO = new CreateVendedorDTO();
        vendedorDTO.setTipoContratacao(TipoContratacaoEnum.PJ);
        vendedorDTO.setCpfCnpj("invalid-cnpj");

        assertFalse(DocUtil.isValidCNPJ(vendedorDTO.getCpfCnpj()));

        assertThrows(IllegalArgumentException.class, () -> {
            ValidatorUtil.validateDocument(vendedorDTO);
        }, "CNPJ inválido");
    }

    @Test
    public void testGenerateMatricula() {
        String matriculaOutsourcing = ValidatorUtil.generateMatricula(TipoContratacaoEnum.OUTSOURCING);
        String matriculaClt = ValidatorUtil.generateMatricula(TipoContratacaoEnum.CLT);
        String matriculaPj = ValidatorUtil.generateMatricula(TipoContratacaoEnum.PJ);

        assertTrue(matriculaOutsourcing.endsWith("-OUT"));
        assertTrue(matriculaClt.endsWith("-CLT"));
        assertTrue(matriculaPj.endsWith("-PJ"));
    }

    @Test
    public void testGenerateMatriculaWithInvalidTipoContratacao() {
        assertThrows(NullPointerException.class, () -> {
            ValidatorUtil.generateMatricula(null);
        }, "Tipo de contratação inválido");
    }
}
