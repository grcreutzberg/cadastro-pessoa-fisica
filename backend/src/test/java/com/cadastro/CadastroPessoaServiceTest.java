package com.cadastro;

import com.cadastro.DTO.CadastroResponse;
import com.cadastro.DTO.PessoaFisicaDTO;
import com.cadastro.model.PessoaFisica;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CadastroPessoaServiceTest {

    @InjectMocks
    private CadastroPessoaService service;

    @Mock
    private CadastroPessoaRepository repository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("12345678901");
        pessoaFisica.setNome("Teste");

        when(repository.findAll()).thenReturn(Arrays.asList(pessoaFisica));

        CadastroResponse<List<PessoaFisica>> response = service.getAll();

        assertEquals(1, response.getContent().size());
        assertEquals("12345678901", response.getContent().get(0).getCpf());
        assertEquals("Teste", response.getContent().get(0).getNome());
    }

    @Test
    public void testGetPessoaFisicaByCPF() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("12345678901");
        pessoaFisica.setNome("Teste");

        when(repository.findById("12345678901")).thenReturn(Optional.of(pessoaFisica));

        CadastroResponse<PessoaFisica> response = service.getPessoaFisicaByCPF("12345678901");

        assertEquals("12345678901", response.getContent().getCpf());
        assertEquals("Teste", response.getContent().getNome());
    }

    // Adicione mais testes para os outros métodos
    @Test
    public void testNovoPessoaFisica() {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste");

        when(repository.findById("12345678901")).thenReturn(Optional.empty());
        when(repository.save(any(PessoaFisica.class))).thenReturn(new PessoaFisica("12345678901", "Teste", null, null, null, null, null, null, null, null));

        CadastroResponse<PessoaFisica> response = service.novoPessoaFisica(pessoaFisicaDTO);

        assertEquals("12345678901", response.getContent().getCpf());
        assertEquals("Teste", response.getContent().getNome());
    }

    @Test
    public void testAtualizarPessoaFisica() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("12345678901");
        pessoaFisica.setNome("Teste");

        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste 2");

        when(repository.findById("12345678901")).thenReturn(Optional.of(pessoaFisica));
        when(repository.save(any(PessoaFisica.class))).thenReturn(new PessoaFisica("12345678901", "Teste 2", null, null, null, null, null, null, null, null));

        CadastroResponse<PessoaFisica> response = service.atualizarPessoaFisica(pessoaFisicaDTO);

        assertEquals("12345678901", response.getContent().getCpf());
        assertEquals("Teste 2", response.getContent().getNome());
    }

    @Test
    public void testDeletarPessoaFisica() {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("12345678901");
        pessoaFisica.setNome("Teste");

        when(repository.findById("12345678901")).thenReturn(Optional.of(pessoaFisica));

        CadastroResponse<PessoaFisica> response = service.deletarPessoaFisica("12345678901");

        assertEquals("12345678901", response.getContent().getCpf());
        assertEquals("Teste", response.getContent().getNome());
    }

    @Test
    public void testDeletarPessoaFisicaNotFound() {
        when(repository.findById("12345678901")).thenReturn(Optional.empty());

        CadastroResponse<PessoaFisica> response = service.deletarPessoaFisica("12345678901");

        assertEquals(CadastroResponse.Status.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testAtualizarPessoaFisicaNotFound() {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste 2");

        when(repository.findById("12345678901")).thenReturn(Optional.empty());

        CadastroResponse<PessoaFisica> response = service.atualizarPessoaFisica(pessoaFisicaDTO);

        assertEquals(CadastroResponse.Status.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testNovoPessoaFisicaCpfJaCadastrado() {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste");

        when(repository.findById("12345678901")).thenReturn(Optional.of(new PessoaFisica()));

        CadastroResponse<PessoaFisica> response = service.novoPessoaFisica(pessoaFisicaDTO);

        assertEquals(CadastroResponse.Status.ERROR, response.getStatus());
    }

    @Test
    public void testNovoPessoaFisicaErroAoSalvar() {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste");

        when(repository.findById("12345678901")).thenReturn(Optional.empty());
        when(repository.save(any(PessoaFisica.class))).thenThrow(new RuntimeException());

        CadastroResponse<PessoaFisica> response = service.novoPessoaFisica(pessoaFisicaDTO);

        assertEquals(CadastroResponse.Status.ERROR, response.getStatus());
    }

    @Test
    public void testGetPessoaFisicaByCPFNotFound() {
        when(repository.findById("12345678901")).thenReturn(Optional.empty());

        CadastroResponse<PessoaFisica> response = service.getPessoaFisicaByCPF("12345678901");

        assertEquals(CadastroResponse.Status.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testAtualizarPessoaFisicaError() {
        PessoaFisicaDTO pessoaFisicaDTO = new PessoaFisicaDTO();
        pessoaFisicaDTO.setCpf("12345678901");
        pessoaFisicaDTO.setNome("Teste 2");

        when(repository.findById("12345678901")).thenReturn(Optional.of(new PessoaFisica()));
        when(repository.save(any(PessoaFisica.class))).thenThrow(new RuntimeException());

        CadastroResponse<PessoaFisica> response = service.atualizarPessoaFisica(pessoaFisicaDTO);

        assertEquals(CadastroResponse.Status.ERROR, response.getStatus());
    }
}