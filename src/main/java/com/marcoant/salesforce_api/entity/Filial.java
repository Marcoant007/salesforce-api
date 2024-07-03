package com.marcoant.salesforce_api.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "filial")
public class Filial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "CNPJ é obrigatório.")
    @CNPJ(message = "CNPJ deve ser válido.")
    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @NotBlank(message = "Cidade é obrigatória.")
    @Column(name = "cidade")
    private String cidade;

    @NotBlank(message = "UF é obrigatória.")
    @Column(name = "uf", length = 2)
    private Short uf;

    @NotBlank(message = "Tipo é obrigatório.")
    @Column(name = "tipo")
    private String tipo;

    @NotNull(message = "Status ativo é obrigatório.")
    @Column(name = "ativo")
    @ColumnDefault("false")
    private Boolean ativo;

    @CreationTimestamp
    @Column(name = "data_cadastro", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "ultima_atualizacao", columnDefinition = "TIMESTAMP")
    private LocalDateTime ultimaAtualizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Short getUf() {
        return uf;
    }

    public void setUf(Short uf) {
        this.uf = uf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
