package com.cadastro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica {

    @Id
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cep")
    private String cep;
    @Column(name = "estado")
    private String estado;
    @Column(name = "municipio")
    private String municipio;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "complemento")
    private String complemento;

    public PessoaFisica() {
    }

    public PessoaFisica(String cpf, String nome, String telefone, String cep, String estado, String municipio, String bairro, String numero, String logradouro,String complemento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.cep = cep;
        this.estado = estado;
        this.municipio = municipio;
        this.bairro = bairro;
        this.numero = numero;
        this.logradouro = logradouro;
        this.complemento = complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
