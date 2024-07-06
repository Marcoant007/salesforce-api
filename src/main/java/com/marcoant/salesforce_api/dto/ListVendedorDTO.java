package com.marcoant.salesforce_api.dto;

public class ListVendedorDTO extends CreateVendedorDTO {

    private FilialDTO filial;

    public FilialDTO getFilial() {
        return filial;
    }

    public void setFilial(FilialDTO filial) {
        this.filial = filial;
    }
}
