package com.cadastro;

import com.cadastro.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroPessoaRepository extends JpaRepository<PessoaFisica, String> {

}
