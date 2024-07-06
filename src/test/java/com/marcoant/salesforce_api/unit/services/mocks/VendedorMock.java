package com.marcoant.salesforce_api.unit.services.mocks;

import java.time.LocalDate;

import com.marcoant.salesforce_api.dto.CreateVendedorDTO;
import com.marcoant.salesforce_api.dto.FilialDTO;
import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;

public class VendedorMock {

    public static CreateVendedorDTO createVendedorDTO() {
        CreateVendedorDTO dto = new CreateVendedorDTO();
        dto.setNome("Marco Antônio");
        dto.setCpfCnpj("12345678909");
        dto.setEmail("marcoantnovo@gmail.com");
        dto.setDataNascimento(LocalDate.of(1998, 2, 27));
        dto.setTipoContratacao(TipoContratacaoEnum.CLT);
        dto.setFilialId(1L);
        return dto;
    }

    public static Vendedor vendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setNome("Marco Antônio");
        vendedor.setCpfCnpj("12345678909");
        vendedor.setEmail("marcoantnovo@gmail.com");
        vendedor.setDataNascimento(LocalDate.of(1998, 2, 27));
        vendedor.setMatricula("12345678-CLT");
        vendedor.setFilialId(1L);
        vendedor.setTipoContratacao(TipoContratacaoEnum.CLT);
        return vendedor;
    }

    public static FilialDTO filialDTO() {
        FilialDTO filialDTO = new FilialDTO();
        filialDTO.setId(1L);
        filialDTO.setNome("Filial Vitória");
        filialDTO.setAtivo(true);
        filialDTO.setCidade("Vitória");
        filialDTO.setCnpj("04616020000142");
        return filialDTO;
    }
}
