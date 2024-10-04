package com.cadastro;

import com.cadastro.DTO.CadastroResponse;
import com.cadastro.DTO.PessoaFisicaDTO;
import com.cadastro.model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

@Service
public class CadastroPessoaService {

    private final CadastroPessoaRepository repository;

    @Autowired
    public CadastroPessoaService(CadastroPessoaRepository repository) {
        this.repository = repository;
    }

    public CadastroResponse<List<PessoaFisica>> getAll() {
        final List<PessoaFisica> pessoaFisicaList = repository.findAll();
        if (pessoaFisicaList.isEmpty()) {
            return CadastroResponse.notFound("Nenhuma pessoa encontrada");
        }
        return CadastroResponse.ok(pessoaFisicaList);
    }

    public CadastroResponse<PessoaFisica> getPessoaFisicaByCPF(String cpf) {
        final PessoaFisica pessoaFisica = repository.findById(cpf).orElse(null);
        if (pessoaFisica == null) {
            return CadastroResponse.notFound("Pessoa não encontrada");
        }
        return CadastroResponse.ok(pessoaFisica);
    }

    public CadastroResponse<PessoaFisica> novoPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        if (repository.findById(pessoaFisicaDTO.getCpf()).isPresent()) {
            return CadastroResponse.error("CPF já cadastrado");
        }

        try {
            final PessoaFisica pessoaFisica = repository.save(new PessoaFisica(
                    pessoaFisicaDTO.getCpf(),
                    pessoaFisicaDTO.getNome(),
                    pessoaFisicaDTO.getTelefone(),
                    pessoaFisicaDTO.getCep(),
                    pessoaFisicaDTO.getEstado(),
                    pessoaFisicaDTO.getMunicipio(),
                    pessoaFisicaDTO.getBairro(),
                    pessoaFisicaDTO.getNumero(),
                    pessoaFisicaDTO.getLogradouro(),
                    pessoaFisicaDTO.getComplemento()
            ));
            return CadastroResponse.ok(pessoaFisica);
        } catch (Exception e) {
            return CadastroResponse.error("Erro ao salvar pessoa");
        }
    }

    public CadastroResponse<PessoaFisica> atualizarPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) {
        final PessoaFisica pessoaFisica = repository.findById(pessoaFisicaDTO.getCpf()).orElse(null);
        if (pessoaFisica == null) {
            return CadastroResponse.notFound("Pessoa não encontrada");
        }

        try {
            pessoaFisica.setNome(pessoaFisicaDTO.getNome());
            pessoaFisica.setTelefone(pessoaFisicaDTO.getTelefone());
            pessoaFisica.setCep(pessoaFisicaDTO.getCep());
            pessoaFisica.setEstado(pessoaFisicaDTO.getEstado());
            pessoaFisica.setMunicipio(pessoaFisicaDTO.getMunicipio());
            pessoaFisica.setBairro(pessoaFisicaDTO.getBairro());
            pessoaFisica.setNumero(pessoaFisicaDTO.getNumero());
            pessoaFisica.setComplemento(pessoaFisicaDTO.getComplemento());

            repository.save(pessoaFisica);
            return CadastroResponse.ok(pessoaFisica);
        } catch (Exception e) {
            return CadastroResponse.error("Erro ao atualizar pessoa");
        }
    }

    public CadastroResponse<PessoaFisica> deletarPessoaFisica(String cpf) {
        final PessoaFisica pessoaFisica = repository.findById(cpf).orElse(null);
        if (pessoaFisica == null) {
            return CadastroResponse.notFound("Pessoa não encontrada");
        }

        try {
            repository.delete(pessoaFisica);
            return CadastroResponse.ok(pessoaFisica);
        } catch (Exception e) {
            return CadastroResponse.error("Erro ao deletar pessoa");
        }
    }

    public CadastroResponse<byte[]> relatorioCSV() {
        final List<PessoaFisica> pessoaFisicaList = repository.findAll();
        if (pessoaFisicaList.isEmpty()) {
            return CadastroResponse.notFound("Nenhuma pessoa encontrada");
        }

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(out);

            // Cabeçalhos do CSV
            writer.println("CPF,Nome,Telefone,CEP,Estado,Município,Bairro,Número,Logradouro,Complemento");

            // Populando o CSV com os dados
            for (PessoaFisica pessoa : pessoaFisicaList) {
                writer.println(pessoa.getCpf() + "," +
                        pessoa.getNome() + "," +
                        pessoa.getTelefone() + "," +
                        pessoa.getCep() + "," +
                        pessoa.getEstado() + "," +
                        pessoa.getMunicipio() + "," +
                        pessoa.getBairro() + "," +
                        pessoa.getNumero() + "," +
                        pessoa.getLogradouro() + "," +
                        pessoa.getComplemento());
            }

            writer.flush();
            writer.close();

            return CadastroResponse.ok(out.toByteArray());
        } catch (Exception e) {
            return CadastroResponse.error("Erro ao gerar relatório");
        }
    }
}
