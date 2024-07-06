package com.marcoant.salesforce_api.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.marcoant.salesforce_api.entity.Vendedor;
import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import org.modelmapper.ModelMapper;

public class CreateVendedorDTO {

    private String matricula;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;
    
    @NotNull(message = "Tipo de contratação é obrigatório")
    private TipoContratacaoEnum tipoContratacao;

    private Long filialId;

    public static CreateVendedorDTO fromVendedor(Vendedor vendedor) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vendedor, CreateVendedorDTO.class);
    }

    public static List<CreateVendedorDTO> fromVendedorList(List<Vendedor> vendedores) {
        return vendedores.stream().map(CreateVendedorDTO::fromVendedor).collect(Collectors.toList());
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

    public Long getFilialId() {
        return filialId;
    }

    public void setFilialId(Long filialId) {
        this.filialId = filialId;
    }
}
