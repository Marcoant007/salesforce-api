package com.marcoant.salesforce_api.dto;

import java.time.LocalDate;

import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;
import org.modelmapper.ModelMapper;

public class VendedorDTO {

    private String matricula;
    private String nome;
    private LocalDate dataNascimento;
    private String cpfCnpj;
    private String email;
    private TipoContratacaoEnum tipoContratacao;

    public static VendedorDTO fromVendedorDTO(Vendedor vendedor) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vendedor, VendedorDTO.class);
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContratacaoEnum getTipoContratacao() {
        return tipoContratacao;
    }

    public void setTipoContratacao(TipoContratacaoEnum tipoContratacao) {
        this.tipoContratacao = tipoContratacao;
    }

}
