package com.marcoant.salesforce_api.entity;

import java.time.LocalDate;

import com.marcoant.salesforce_api.enums.TipoContratacaoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Matrícula é obrigatória.")
    @Pattern(regexp = ".*-OUT|.*-CLT|.*-PJ", message = "Matrícula deve terminar com -OUT, -CLT ou -PJ.")
    @Column(name = "matricula", unique = true)
    private String matricula;

    @NotBlank(message = "Nome é obrigatório.")
    @Column(name = "nome")
    private String nome;
   
    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @NotBlank(message = "CPF ou CNPJ é obrigatório.")
    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @NotBlank(message = "E-mail é obrigatório.")
    @Column(name = "email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contratacao")
    private TipoContratacaoEnum tipoContratacao;

    @Column(nullable = false)
    private Long filialId; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
