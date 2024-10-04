package com.cadastro.DTO;

public class PessoaFisicaDTO {

    private String cpf;
    private String nome;
    private String telefone;
    private String cep;
    private String estado;
    private String municipio;
    private String bairro;
    private String numero;
    private String logradouro;
    private String complemento;

    public PessoaFisicaDTO(String cpf, String nome, String telefone, String cep, String estado, String municipio, String bairro, String numero, String logradouro, String complemento) {
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

    public PessoaFisicaDTO() {

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
