package com.cadastro;

import com.cadastro.DTO.CadastroResponse;
import com.cadastro.DTO.PessoaFisicaDTO;
import com.cadastro.model.PessoaFisica;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoa-fisica")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
public class CadastroPessoaEndpoint {

    private final CadastroPessoaService service;
    public CadastroPessoaEndpoint(CadastroPessoaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<CadastroResponse<List<PessoaFisica>>> getAll() {
        final CadastroResponse<List<PessoaFisica>> response = service.getAll();

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CadastroResponse<PessoaFisica>> find(@PathVariable String cpf) {
        final CadastroResponse<PessoaFisica> response = service.getPessoaFisicaByCPF(cpf);

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CadastroResponse<PessoaFisica>> insert(@RequestBody PessoaFisicaDTO pessoaFisicaDTO) {
        final CadastroResponse<PessoaFisica> response = service.novoPessoaFisica(pessoaFisicaDTO);

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<CadastroResponse<PessoaFisica>> update(@RequestBody PessoaFisicaDTO pessoaFisicaDTO) {
        final CadastroResponse<PessoaFisica> response = service.atualizarPessoaFisica(pessoaFisicaDTO);

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<CadastroResponse<PessoaFisica>> delete(@PathVariable String cpf) {
        final CadastroResponse<PessoaFisica> response = service.deletarPessoaFisica(cpf);

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/exportCSV", produces = "text/csv")
    public ResponseEntity<byte[]> exportCSV() {
        final CadastroResponse<byte[]> response = service.relatorioCSV();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "text/csv; charset=UTF-8");
        responseHeaders.set("Content-Disposition", "attachment; filename=pessoas.csv");

        if (response.getStatus() == CadastroResponse.Status.NOT_FOUND) {
            return new ResponseEntity<>(response.getContent(), HttpStatus.NOT_FOUND);
        } else if (response.getStatus() == CadastroResponse.Status.ERROR) {
            return new ResponseEntity<>(response.getContent(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response.getContent(), responseHeaders, HttpStatus.OK);
    }
}
