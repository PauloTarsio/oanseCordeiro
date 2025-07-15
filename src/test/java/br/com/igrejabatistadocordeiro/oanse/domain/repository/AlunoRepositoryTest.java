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

import br.com.igrejabatistadocordeiro.oanse.domain.model.Aluno;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Long alunoId;
    private Long enderecoIdAluno;
    private Long enderecoIdIgreja;

    @Test
    public void deveriaExecutarCrudCompletoDoAluno() throws Exception {
        criarAlunoCompleto();
        buscarAlunoPorId();
        atualizarAluno();
        deletarAluno();
    }

    private void criarAlunoCompleto() throws Exception {
        String json = Files.readString(Paths.get("src/test/resources/json/aluno.json"));
        Aluno aluno = objectMapper.readValue(json, Aluno.class);

        Aluno salvo = alunoRepository.save(aluno);

        Assertions.assertNotNull(salvo.getId());
        Assertions.assertNotNull(salvo.getDadosPessoais().getId());
        Assertions.assertNotNull(salvo.getDadosPessoais().getEndereco().getId());
        Assertions.assertNotNull(salvo.getIgreja().getId());

        this.alunoId = salvo.getId();
        this.enderecoIdAluno = salvo.getDadosPessoais().getEndereco().getId(); // guarda o ID do endereço
        this.enderecoIdIgreja = salvo.getIgreja().getDadosPessoais().getEndereco().getId(); // guarda o ID do endereço da igreja

        System.out.println("✔️ Aluno criado com ID: " + salvo.getId());
    }

    private void buscarAlunoPorId() {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        Assertions.assertTrue(alunoOpt.isPresent());
        System.out.println("✔️ Aluno encontrado: " + alunoOpt.get().getDadosPessoais().getDescricao());
    }

    private void atualizarAluno() {
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();

        aluno.getDadosPessoais().setDescricao("Carlos Aluno Atualizado");
        aluno.getDadosPessoais().setEmail("carlos.novo@exemplo.com");
        
        aluno.getDadosPessoais().getEndereco().setId(enderecoIdAluno);
        aluno.getIgreja().getDadosPessoais().getEndereco().setId(enderecoIdIgreja);

        Aluno atualizado = alunoRepository.save(aluno);

        Assertions.assertEquals("Carlos Aluno Atualizado", atualizado.getDadosPessoais().getDescricao());
        Assertions.assertEquals("carlos.novo@exemplo.com", atualizado.getDadosPessoais().getEmail());

        System.out.println("✔️ Aluno atualizado com sucesso.");
    }

    private void deletarAluno() {
        alunoRepository.deleteById(alunoId);

        Optional<Aluno> excluido = alunoRepository.findById(alunoId);
        Assertions.assertTrue(excluido.isEmpty());
        System.out.println("✔️ Aluno deletado com sucesso.");
    }
}
