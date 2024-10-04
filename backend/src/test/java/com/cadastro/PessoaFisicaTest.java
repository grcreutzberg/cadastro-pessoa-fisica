package com.cadastro;

import com.cadastro.model.PessoaFisica;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaFisicaTest {

    @Test
    public void testSetAndGetCpf() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("12345678901");
        assertEquals("12345678901", pessoaFisica.getCpf());
    }

    @Test
    public void testSetAndGetNome() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Teste");
        assertEquals("Teste", pessoaFisica.getNome());
    }

    @Test
    public void testSetAndGetTelefone() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setTelefone("12345678901");
        assertEquals("12345678901", pessoaFisica.getTelefone());
    }

    @Test
    public void testSetAndGetCep() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCep("12345678");
        assertEquals("12345678", pessoaFisica.getCep());
    }

    @Test
    public void testSetAndGetEstado() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setEstado("SP");
        assertEquals("SP", pessoaFisica.getEstado());
    }

    @Test
    public void testSetAndGetMunicipio() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setMunicipio("São Paulo");
        assertEquals("São Paulo", pessoaFisica.getMunicipio());
    }

    @Test
    public void testSetAndGetBairro() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setBairro("Centro");
        assertEquals("Centro", pessoaFisica.getBairro());
    }

    @Test
    public void testSetAndGetNumero() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNumero("123");
        assertEquals("123", pessoaFisica.getNumero());
    }

    @Test
    public void testSetAndGetLogradouro() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setLogradouro("Rua Teste");
        assertEquals("Rua Teste", pessoaFisica.getLogradouro());
    }

    @Test
    public void testSetAndGetComplemento() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setComplemento("Casa");
        assertEquals("Casa", pessoaFisica.getComplemento());
    }
}