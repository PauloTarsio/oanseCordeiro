package br.com.igrejabatistadocordeiro.oanse.domain.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.igrejabatistadocordeiro.oanse.domain.model.DadosPessoais;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Long pessoaId;
    private Long enderecoId;
	
    @Test
    public void deveriaPassarPeloCrudCompleto() throws Exception {
        criarPessoaComEndereco();
        buscarPessoaPorId();
        atualizarPessoa();
        deletarPessoa();
    }

    private void criarPessoaComEndereco() throws Exception {
        String json = Files.readString(Paths.get("src/test/resources/json/pessoa.json"));
        DadosPessoais pessoa = objectMapper.readValue(json, DadosPessoais.class);

        DadosPessoais salvo = pessoaRepository.save(pessoa);

        Assertions.assertNotNull(salvo.getId());
        Assertions.assertNotNull(salvo.getEndereco().getId());
        System.out.println("✔️ Pessoa criada com ID: " + salvo.getId());

        this.pessoaId = salvo.getId(); // guarda para os próximos passos
        this.enderecoId = salvo.getEndereco().getId(); // guarda o ID do endereço
    }

    private void buscarPessoaPorId() {
        Optional<DadosPessoais> pessoaOpt = pessoaRepository.findById(pessoaId);
        Assertions.assertTrue(pessoaOpt.isPresent());
        System.out.println("✔️ Pessoa encontrada: " + pessoaOpt.get().getDescricao());
    }

    private void atualizarPessoa() {
        DadosPessoais pessoa = pessoaRepository.findById(pessoaId).orElseThrow();

        pessoa.setEmail("nova.email@exemplo.com");
        pessoa.setDescricao("Nome Atualizado");
        
        pessoa.getEndereco().setId(enderecoId);
        pessoa.getEndereco().setRua("Endereço Atualizado");

        DadosPessoais atualizada = pessoaRepository.save(pessoa);

        Assertions.assertEquals("nova.email@exemplo.com", atualizada.getEmail());
        Assertions.assertEquals("Nome Atualizado", atualizada.getDescricao());
        Assertions.assertEquals("Endereço Atualizado", atualizada.getEndereco().getRua());
        System.out.println("✔️ Pessoa atualizada com novo e-mail e nome.");
    }

    private void deletarPessoa() {
        pessoaRepository.deleteById(pessoaId);

        Optional<DadosPessoais> excluida = pessoaRepository.findById(pessoaId);
        Assertions.assertTrue(excluida.isEmpty());
        System.out.println("✔️ Pessoa deletada com sucesso.");
    }

}
